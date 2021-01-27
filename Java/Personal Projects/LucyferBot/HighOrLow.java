package games;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import owner.Settings;

public class HighOrLow extends Command {
  private EventWaiter waiter;
  private static int firstNumber = 0;
  private static int secondNumber = 0;
  private static String playerID;
  private static boolean currentlyPlaying = false;

  public HighOrLow(EventWaiter waiter) {
    this.name = "highorlow";
    this.aliases = new String[] { "highorlow", "hol" };
    this.help = "Guess whether the next number will be higher or lower!";
    this.waiter = waiter;
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    if (!getCurrentlyPlaying()) { // No ongoing game
      Random rand = new Random();
      setFirstNumber(rand.nextInt(101) + 1);
      setSecondNumber(rand.nextInt(101) + 1);
      setPlayerID(e.getMember().getUser().getId());
      setCurrentlyPlaying(true);
      while (getFirstNumber() == getSecondNumber()) {
        setSecondNumber(rand.nextInt(101) + 1);
      }
      EmbedBuilder display = new EmbedBuilder();
      display.setTitle("__HighOrLow__");
      display.setDescription("My number is (" + getFirstNumber() + ") from a range of numbers from 1 - 100. "
          + "\nWill the next number I think of be higher or lower?");
      sendEmbed(e, display);
      new java.util.Timer().schedule(new java.util.TimerTask() { // Game Non-action Timeout (15s)
        public void run() {
          if (getCurrentlyPlaying()) {
            EmbedBuilder display = new EmbedBuilder();
            display.setTitle("__HighOrLow__");
            display
                .setDescription(e.getMember().getAsMention() + " took too long to choose, and the game has expired!");
            sendEmbed(e, display);
            setCurrentlyPlaying(false);
          }
        }
      }, 15000);
    } else {
      e.getChannel()
          .sendMessage("A high or low game is currently being played. Please wait until it finishes or expires.")
          .queue();
    }
    waiter.waitForEvent(GuildMessageReceivedEvent.class, w -> !w.getMessage().getEmbeds().isEmpty() // Add Reactions
        && (w.getMessage().getEmbeds().get(0).getTitle().equals("__HighOrLow__")), w -> {
          w.getMessage().addReaction("🔼").queue();
          w.getMessage().addReaction("🔽").queue();
        }, 15, TimeUnit.SECONDS, () -> {
        });
    waiter // Lock Reactions to Requester
        .waitForEvent(GuildMessageReactionAddEvent.class,
            w -> w.getMember().getUser().getId().equals(getPlayerID())
                && (w.getReactionEmote().getName().equals("🔼") || (w.getReactionEmote().getName().equals("🔽"))),
            w -> {
              results(e);
            }, 15, TimeUnit.SECONDS, () -> {
            });
  }

  private static int getFirstNumber() {
    return HighOrLow.firstNumber;
  }

  private static int getSecondNumber() {
    return HighOrLow.secondNumber;
  }

  public static String getPlayerID() {
    return HighOrLow.playerID;
  }

  private static boolean getCurrentlyPlaying() {
    return HighOrLow.currentlyPlaying;
  }

  private static void setFirstNumber(int firstNumber) {
    HighOrLow.firstNumber = firstNumber;
  }

  private static void setSecondNumber(int secondNumber) {
    HighOrLow.secondNumber = secondNumber;
  }

  private static void setPlayerID(String playerID) {
    HighOrLow.playerID = playerID;
  }

  private static void setCurrentlyPlaying(boolean status) {
    HighOrLow.currentlyPlaying = status;
  }

  public void results(CommandEvent e) {
    EmbedBuilder display = new EmbedBuilder();
    if (getFirstNumber() > getSecondNumber()) {
      display.setTitle("__HighOrLow__");
      display.setDescription("||I thought of (" + getSecondNumber() + "). The number was lower!||");
    } else {
      display.setTitle("__HighOrLow__");
      display.setDescription("||I thought of (" + getSecondNumber() + "). The number was higher!||");
    }
    sendEmbed(e, display);
    setCurrentlyPlaying(false);
    setPlayerID("");
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}
