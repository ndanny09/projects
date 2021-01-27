package utility;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import owner.Settings;

public class WhoIs extends Command {
  public WhoIs() {
    this.name = "whois";
    this.aliases = new String[] { "whois", "profile", "user" };
    this.arguments = "[0]Self [1]Mention/UserID";
    this.help = "Provides information on the user.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    EmbedBuilder display = new EmbedBuilder();
    if (arguments == 1) { // Self
      User user = e.getMember().getUser();
      profile(e, display, user, true, true, "");
    } else if (arguments == 2) { // Mention || UserID
      if (!e.getMessage().getMentionedUsers().isEmpty()) { // Mention
        User user = e.getMessage().getMentionedUsers().get(0);
        profile(e, display, user, false, true, "");
      } else {
        if (args[1].length() == 18) { // UserID
          String userID = args[1];
          User user = e.getJDA().retrieveUserById(userID).complete();
          profile(e, display, user, false, false, userID);
        }
      }
    }
  }

  private void profile(CommandEvent e, EmbedBuilder display, User user, Boolean self, Boolean notID, String userID) {
    display.setTitle("__Profile__");
    display.setThumbnail(user.getAvatarUrl());
    Boolean idInGuild = false;
    Member memberReferencedByID = null;
    if (!notID) { // User ID
      try { // Check if User exists in the Guild
        memberReferencedByID = getMemberByID(e, userID);
        idInGuild = true;
      } catch (ErrorResponseException error) {
      }
    }
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    display.setDescription("**Tag:** " + user.getAsMention() + "\n**Discord:** `" + user.getAsTag()
        + "`\n**User ID:** `" + user.getId() + "`\n**Created:** `" + user.getTimeCreated().format(dtf) + " GMT`"
        + (self ? "\n**Joined:** `" + e.getMember().getTimeJoined().format(dtf) + " GMT`"
            : notID ? "\n**Joined:** `" + getMemberByID(e, user.getId()).getTimeJoined().format(dtf) + " GMT`"
                : idInGuild ? "\n**Joined:** `" + memberReferencedByID.getTimeJoined().format(dtf) + " GMT`" : ""));
    if (notID || idInGuild) { // User exists in the Guild to parse Role List
      display.addField("**Roles:**",
          parseRoleList(
              self ? e.getMember() : notID ? e.getMessage().getMentionedMembers().get(0) : memberReferencedByID),
          false);
    }
    sendEmbed(e, display);
  }

  private Member getMemberByID(CommandEvent e, String userID) {
    return e.getGuild().retrieveMemberById(userID).complete();
  }

  private String parseRoleList(Member member) {
    String roleList = "";
    String roleID;
    int roleIDStart, roleIDEnd;
    for (Role roleLongID : member.getRoles()) { // Loop, extract ID, remove excess data, convert to mentionable
      roleID = roleLongID.toString();
      roleIDStart = roleID.length() - 19;
      roleIDEnd = roleID.length() - 1;
      roleList += "<@&" + roleID.substring(roleIDStart, roleIDEnd) + "> ";
    }
    return roleList;
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}
