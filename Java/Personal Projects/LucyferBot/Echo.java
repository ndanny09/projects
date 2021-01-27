package miscellaneous;

import java.time.Instant;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class Echo extends Command {
  public Echo() {
    this.name = "echo";
    this.aliases = new String[] { "echo", "repeat", "copycat" };
    this.arguments = "[0++]Text";
    this.help = "Repeats user's text.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    if (e.getMessage().getAttachments().isEmpty()) {
      EmbedBuilder display = new EmbedBuilder();
      display.setDescription(e.getMember().getAsMention() + ":" + e.getMessage().getContentDisplay());
      sendEmbed(e, display);
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
