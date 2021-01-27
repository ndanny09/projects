package utility;

import java.time.Instant;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import owner.Settings;

public class Avatar extends Command {
  public Avatar() {
    this.name = "avatar";
    this.aliases = new String[] { "avatar", "pfp" };
    this.arguments = "[0]Self [1]Mention/UserID/Size [2]Size";
    this.help = "Provides the user's profile picture.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    String defaultAvatarSize = "1024"; // Choice between 128, 256, 512, 1024
    boolean mention = (!e.getMessage().getMentionedMembers().isEmpty());
    EmbedBuilder display = new EmbedBuilder();
    if (arguments == 1) { // Self
      User user = e.getMember().getUser();
      setEmbed(display, user.getName(), "Resolution: " + defaultAvatarSize + "x" + defaultAvatarSize,
          user.getAvatarUrl() + "?size=" + defaultAvatarSize);
      sendEmbed(e, display);
    } else if (arguments == 2) { // Mention || UserID || Self & Size
      if (mention) { // Mention
        Member user = e.getMessage().getMentionedMembers().get(0);
        setEmbed(display, user.getEffectiveName(), "Resolution: " + defaultAvatarSize + "x" + defaultAvatarSize,
            user.getUser().getAvatarUrl() + "?size=" + defaultAvatarSize);
        sendEmbed(e, display);
      } else { // User ID || Self & Size
        if (args[1].length() == 18) { // UserID
          try {
            User user = e.getJDA().retrieveUserById(args[1]).complete();
            setEmbed(display, user.getName(), "Resolution: " + defaultAvatarSize + "x" + defaultAvatarSize,
                user.getAvatarUrl() + "?size=" + defaultAvatarSize);
            sendEmbed(e, display);
          } catch (ErrorResponseException error) {
            e.getChannel().sendMessage("Invalid User ID.").queue();
          }
        } else { // Self & Size
          User user = e.getMember().getUser();
          setEmbed(display, user.getName(), null,
              user.getAvatarUrl() + "?size=" + imageSize(args, 1, defaultAvatarSize, display));
          sendEmbed(e, display);
        }
      }
    } else if (arguments == 3) { // Mention & Size, UserID & Size
      if (mention) {
        Member user = e.getMessage().getMentionedMembers().get(0);
        setEmbed(display, user.getEffectiveName(), null,
            user.getUser().getAvatarUrl() + "?size=" + imageSize(args, 2, defaultAvatarSize, display));
        sendEmbed(e, display);
      } else {
        if (args[1].length() == 18) { // UserID & Size
          try {
            User user = e.getJDA().retrieveUserById(args[1]).complete();
            setEmbed(display, user.getName(), null,
                user.getAvatarUrl() + "?size=" + imageSize(args, 2, defaultAvatarSize, display));
            sendEmbed(e, display);
          } catch (ErrorResponseException error) {
            e.getChannel().sendMessage("Invalid User ID.").queue();
          }
        }
      }
    }
  }

  private void setEmbed(EmbedBuilder display, String title, String description, String image) {
    display.setTitle(title);
    display.setDescription(description);
    display.setImage(image);
  }

  private String imageSize(String[] args, int argument, String avatarSize, EmbedBuilder display) {
    if (args[argument].equals("128") || args[argument].equals("256") || args[argument].equals("512")) {
      avatarSize = args[argument];
    }
    display.setDescription("Resolution: " + avatarSize + "x" + avatarSize);
    return avatarSize;
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}