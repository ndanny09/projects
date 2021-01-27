package about;

import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import owner.Settings;

public class Info extends Command {
  public Info() {
    this.name = "info";
    this.aliases = new String[] { "info", "about" };
    this.help = "Provides information on the bot and its developer.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    // Uptime Clock
    // Credit to Almighty Alpaca - slightly modified version
    // https://github.com/Java-Discord-Bot-System/Plugin-Uptime/blob/master/src/main/java/com
    // /almightyalpaca/discord/bot/plugin/uptime/UptimePlugin.java#L28-L42
    final long duration = ManagementFactory.getRuntimeMXBean().getUptime();
    final long years = duration / 31104000000L;
    final long months = duration / 2592000000L % 12;
    final long days = duration / 86400000L % 30;
    final long hours = duration / 3600000L % 24;
    final long minutes = duration / 60000L % 60;
    final long seconds = duration / 1000L % 60;
    String uptime = (years == 0 ? "" : +years + "yr ") + (months == 0 ? "" : +months + "mo ")
        + (days == 0 ? "" : +days + "d ") + (hours == 0 ? "" : +hours + "h ") + (minutes == 0 ? "" : +minutes + "m ")
        + (seconds == 0 ? "" : +seconds + "s");
    JDA jda = e.getJDA();
    EmbedBuilder display = new EmbedBuilder();
    display.setTitle("__Info__");
    display.setDescription("**Developer:** " + jda.getUserById("204448598539239424").getAsMention()
        + "\n**Bot:** " + jda.getSelfUser().getAsMention() + "\n**Created:** `"
        + jda.getSelfUser().getTimeCreated().format(dtf) + " GMT` \n**Version:** `UNRELEASED` "
        + "\n**Language:** `Java` \n**Source:** `UNRELEASED` \n**Uptime:** " + uptime);
    display.setThumbnail(jda.getSelfUser().getAvatarUrl());
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