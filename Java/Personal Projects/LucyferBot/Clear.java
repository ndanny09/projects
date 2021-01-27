package owner;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class Clear extends Command {
  public Clear() {
    this.name = "clear";
    this.aliases = new String[] { "clear", "purge", "clean" };
    this.arguments = "[1]Number";
    this.help = "Clears a number of [2-100] messages.";
    this.ownerCommand = true;
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    if (arguments == 2) {
      try {
        int number = Integer.parseInt(args[1]);
        MessageChannel channel = e.getChannel();
        clearMessages(channel, number);
      } catch (NumberFormatException error) {
        e.getChannel().sendMessage("You must provide a number of (2-100) messages to clear.").queue();
      }
    }
  }

  private void clearMessages(MessageChannel channel, int number) {
    if (number >= 2 && number <= 100) {
      List<Message> messages = channel.getHistory().retrievePast(number).complete();
      channel.purgeMessages(messages);
      channel.sendMessage("Previous (" + number + ") messages cleared.").queue();
    } else {
      channel.sendMessage("You must provide a number of (2-100) messages to clear.").queue();
    }
  }
}
