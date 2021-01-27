package games;

import java.time.Instant;
import java.util.Random;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class Flip extends Command {
  public Flip() {
    this.name = "flip";
    this.aliases = new String[] { "flip", "coinflip", "headsortails" };
    this.arguments = "[0]Once [1]Number";
    this.help = "Flips a coin.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    if (arguments == 1) { // Flip coin once
      EmbedBuilder display = new EmbedBuilder();
      display.setTitle("__Roll__");
      display.setDescription(randomFlip(1));
      sendEmbed(e, display);
    } else if (arguments == 2) { // Flip coin multiple times
      try {
        int flips = Integer.parseInt(args[1]);
        if ((flips >= 1) && (flips <= 10)) {
          EmbedBuilder display = new EmbedBuilder();
          display.setTitle("__Flips__");
          display.setDescription(randomFlip(flips));
          sendEmbed(e, display);
        } else {
          e.getChannel().sendMessage("You need to specify a number between (1-10) times to flip the coin.").queue();
        }
      } catch (NumberFormatException error) {
        e.getChannel().sendMessage("You need to specify a number between (1-10) times to flip the coin.").queue();
      }
    }
  }

  public static String randomFlip(int flips) {
    Random rand = new Random();
    String flipResults = "";
    if (flips == 1) {
      if (rand.nextInt(2) == 0) {
        flipResults = "You flipped a **Heads**.";
      } else {
        flipResults = "You flipped a **Tails**.";
      }
    } else if (flips > 1) {
      for (int i = 0; i < flips; i++) {
        String flipResult;
        if (rand.nextInt(2) == 0) {
          flipResult = "Heads";
        } else {
          flipResult = "Tails";
        }
        flipResults += "\n" + (i + 1) + ": **(" + flipResult + ")** ";
      }
    }
    return flipResults;
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}
