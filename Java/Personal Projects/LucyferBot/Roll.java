package games;

import java.time.Instant;
import java.util.Random;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class Roll extends Command {
  public Roll() {
    this.name = "roll";
    this.aliases = new String[] { "roll", "rng", "dice" };
    this.arguments = "[0]Once [1]Number [2]LowerBound [3]UpperBound";
    this.help = "Dice roll and integer RNG (random number generator).";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    if (arguments == 1) { // Roll six sided die once
      EmbedBuilder display = new EmbedBuilder();
      display.setTitle("__Roll__");
      display.setDescription(randomRoll(1, 7, 1));
      sendEmbed(e, display);
    } else if (arguments == 2) { // Roll six sided die multiple times
      try {
        int rolls = Integer.parseInt(args[1]);
        if ((rolls >= 1) && (rolls <= 10)) {
          EmbedBuilder display = new EmbedBuilder();
          display.setTitle("__Rolls__");
          display.setDescription(randomRoll(1, 7, rolls));
          sendEmbed(e, display);
        } else {
          e.getChannel().sendMessage("You need to specify a number between (1-10) times to roll the dice.").queue();
        }
      } catch (NumberFormatException error) {
        e.getChannel().sendMessage("You need to specify a number between (1-10) times to roll the dice.").queue();
      }
    } else if (arguments == 4) { // Custom RNG roll
      try {
        int rolls = Integer.parseInt(args[1]);
        int lowerBound = Integer.parseInt(args[2]);
        int upperBound = Integer.parseInt(args[3]);
        if ((lowerBound >= 0) && (upperBound >= 0)) {
          if (lowerBound != upperBound) {
            if ((rolls >= 1) && (rolls <= 10)) {
              try {
                EmbedBuilder display = new EmbedBuilder();
                display.setTitle("__RNG__");
                display.setDescription(randomRoll(lowerBound, upperBound, rolls));
                sendEmbed(e, display);
              } catch (IllegalArgumentException error) {
                e.getChannel().sendMessage("You cannot set the lower bound higher than the upper bound.").queue();
              }
            } else {
              e.getChannel().sendMessage("You need to specify a number between (1-10) times to roll.").queue();
            }
          } else {
            e.getChannel().sendMessage("You cannot set the lower and upper bound as the same values.").queue();
          }
        } else {
          e.getChannel().sendMessage("You can only use positive integers.").queue();
        }
      } catch (NumberFormatException error) {
        e.getChannel().sendMessage("One of the arguments is invalid.").queue();
      }
    } else { // Invalid number of arguments
      e.getChannel().sendMessage("That's not a valid argument format for the roll command.").queue();
    }
  }

  public static String randomRoll(int lowerBound, int upperBound, int rolls) {
    Random rand = new Random();
    String rollResults = "";
    if (rolls == 1) { // Roll Once
      rollResults = "You rolled a **(" + Integer.toString(rand.nextInt(upperBound - lowerBound) + lowerBound) + ")**.";
    } else if (rolls > 1) { // Multiple rolls
      for (int i = 0; i < rolls; i++) {
        String rollResult = Integer.toString(rand.nextInt(upperBound - lowerBound) + lowerBound);
        rollResults += "\n" + (i + 1) + ": **(" + rollResult + ")** ";
      }
    }
    return rollResults;
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}
