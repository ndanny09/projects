package lucyfer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageLog extends ListenerAdapter {
  public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
    // MM/dd(HH:mm)<Server>#channel[UserTag]:Text(MessageAttachment)
    if (isHuman(e)) {
      sendMessageLog(e);
    }
  }

  public static void sendMessageLog(GuildMessageReceivedEvent e) {
    System.out.println(getTime() + getGuildName(e) + getChannelName(e) + getAuthorTag(e) + getMessageContent(e)
        + (!e.getMessage().getAttachments().isEmpty() ? getMessageAttachment(e) : ""));
  }

  private boolean isHuman(GuildMessageReceivedEvent e) {
    return (!e.getMessage().isWebhookMessage() && (!e.getMessage().getAuthor().isBot()));
  }

  public static String getTime() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd(HH:mm)");
    LocalDateTime currentDateTime = LocalDateTime.now();
    return currentDateTime.format(dtf);
  }

  public static String getGuildName(GuildMessageReceivedEvent e) {
    return "<" + e.getGuild().getName() + ">";
  }

  public static String getChannelName(GuildMessageReceivedEvent e) {
    return "#" + e.getChannel().getName() + "";
  }

  public static String getAuthorTag(GuildMessageReceivedEvent e) {
    return "[" + e.getAuthor().getAsTag() + "]:";
  }

  public static String getMessageContent(GuildMessageReceivedEvent e) {
    return (!e.getMessage().getContentDisplay().isEmpty() ? e.getMessage().getContentDisplay() + " " : "");
  }

  public static String getMessageAttachment(GuildMessageReceivedEvent event) {
    return "(" + event.getMessage().getAttachments().get(0).getUrl() + ")";
  }
}