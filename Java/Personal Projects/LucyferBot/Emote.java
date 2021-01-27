package utility;

import java.time.Instant;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class Emote extends Command {
  public Emote() {
    this.name = "emote";
    this.aliases = new String[] { "emote", "emoji" };
    this.arguments = "[1]Emote";
    this.help = "Provides mentioned emote as a file.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    if (!e.getMessage().getEmotes().isEmpty()) {
      EmbedBuilder display = new EmbedBuilder();
      display.setTitle(e.getMessage().getEmotes().get(0).getName());
      display.setImage(e.getMessage().getEmotes().get(0).getImageUrl());
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