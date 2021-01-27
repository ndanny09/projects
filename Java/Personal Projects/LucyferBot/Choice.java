package games;

import java.time.Instant;
import java.util.Random;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class Choice extends Command {
  public Choice() {
    this.name = "choice";
    this.aliases = new String[] { "choice", "choose", "pick" };
    this.arguments = "[1, ++]Option";
    this.help = "Chooses randomly between any number of options.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    for (String i : args) {
      System.out.println(i);
    }
    if (arguments > 1) { // Choices provided
      String choicesString = "";
      for (int i = 1; i < args.length; i++) {
        choicesString += " " + args[i];
      }
      String[] choices = choicesString.split(",");
      boolean emptyChoiceExists = false;
      int pointer = 0;
      while ((!emptyChoiceExists) && (pointer < choices.length)) {
        if (choices[pointer].equals(" ")) {
          emptyChoiceExists = true;
        }
        pointer++;
      }
      if (emptyChoiceExists) {
        e.getChannel().sendMessage("None of the choices provided can be empty.").queue();
      } else {
        Random rand = new Random();
        int chosen = rand.nextInt(choices.length);
        EmbedBuilder display = new EmbedBuilder();
        display.setTitle("__Choice__");
        display.setDescription("Based on the choices you provided... \n\n" + choicesString.substring(1)
            + "\n\n**I have chosen:** \n||" + choices[chosen] + "||");
        sendEmbed(e, display);
      }
    } else { // No arguments provided
      e.getChannel().sendMessage("You need to provide some choices seperated by a comma.").queue();
    }
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}
