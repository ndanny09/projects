package miscellaneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import owner.Settings;

public class Ping extends Command {
  public Ping() {
    this.name = "ping";
    this.aliases = new String[] { "ping", "response" };
    this.help = "Response time of the bot in milliseconds.";
  }

  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    long time = System.currentTimeMillis();
    e.getChannel().sendMessage("Ping:").queue(response -> {
      response.editMessageFormat("Ping: %d ms", System.currentTimeMillis() - time).queue();
    });
  }
}