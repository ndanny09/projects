package owner;

import java.time.Instant;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;

public class Shutdown extends Command {
  public Shutdown() {
    this.name = "shutdown";
    this.aliases = new String[] { "shutdown", "turnoff", "terminate" };
    this.help = "Shuts the bot down.";
    this.ownerCommand = true;
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    EmbedBuilder display = new EmbedBuilder();
    display.setTitle("__Shutdown__");
    display.setDescription("Well, it was fun while it lasted. Change the world.... my final message. Goodbye. "
        + "\n**Lucyfer is shutting down.**");
    display
        .setImage("https://cdn.discordapp.com/attachments/761839761928355840/761839840538394634/lucyferShutdown.gif");
    sendEmbed(e, display);
    e.getJDA().shutdown();
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}