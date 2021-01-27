package utility;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import owner.Settings;

public class ServerInfo extends Command {
  public ServerInfo() {
    this.name = "serverinfo";
    this.aliases = new String[] { "serverinfo", "discord", "server" };
    this.help = "Provides information on the server.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    System.out.println(System.currentTimeMillis());
    Guild guild = e.getGuild();
    EmbedBuilder display = new EmbedBuilder();
    int textChannelCount = guild.getTextChannels().size();
    int voiceChannelCount = guild.getVoiceChannels().size();
    display.setTitle("__" + guild.getName() + "__");
    display.setDescription("**Owner:** " + guild.getOwner().getAsMention() + "\n**Server ID:** `" + guild.getId()
        + "`\n**Time Created:** `" + guild.getTimeCreated().format(dtf) + " GMT`\n**Region:** `"
        + guild.getRegion().getName() + "`\n**Channels:** `" + (textChannelCount + voiceChannelCount) + "` (`"
        + Integer.toString(textChannelCount) + "` Text | `" + Integer.toString(voiceChannelCount) + "` Voice)"
        + "\n**Roles:** `" + Integer.toString(guild.getRoles().size()) + "` **Emotes:** `"
        + Integer.toString(guild.getEmotes().size()) + "` **Boosts:** `" + Integer.toString(guild.getBoostCount())
        + "`");
    display.setThumbnail(guild.getIconUrl());
    display.setImage(guild.getBannerUrl());
    sendEmbed(e, display);
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}
