package owner;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;

public class Settings extends Command {
  private static String prefix = "?";
  private static String alternatePrefix = "L:";
  private static boolean deleteInvoke = false;
  private static boolean embedDecay = false;
  private static int embedDecayTime = 30;

  public Settings() {
    this.name = "settings";
    this.aliases = new String[] { "settings", "config" };
    this.arguments = "[0]Settings [1]Setting [2]True/False";
    this.help = "Provides information on the bot's settings.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    if (arguments == 1) {
      EmbedBuilder display = new EmbedBuilder();
      display.setTitle("__Settings__");
      display.setDescription("**Prefix:** `" + prefix + "` \n**AlternatePrefix:** `" + alternatePrefix
          + "` \n**DeleteInvoke**: `" + deleteInvoke + "`" + "\n**MessageDecay:** `" + embedDecay + "`"
          + "\n**MessageDecayTime:** `" + embedDecayTime + "`");
      sendEmbed(e, display);
    } else if (arguments == 3) {
      if (e.getMember().isOwner()) {
        String setting = args[1].toLowerCase();
        switch (setting) {
        case "deleteinvoke":
          setDeleteInvokeSetting(e, args[2]);
          break;
        case "messagedecay":
          setEmbedDecaySetting(e, args[2]);
          break;
        case "messagedecaytimer":
          setEmbedDecayTimeSetting(e, args[2]);
          break;
        }
      } else {
        e.getChannel().sendMessage("You must be the server owner to change the bot's settings.").queue();
      }
    }
  }

  public static String getPrefix() {
    return Settings.prefix;
  }

  public static String getAlternatePrefix() {
    return Settings.alternatePrefix;
  }

  private static boolean getDeleteInvoke() {
    return Settings.deleteInvoke;
  }

  private static boolean getEmbedDecay() {
    return Settings.embedDecay;
  }

  private static int getEmbedDecayTime() {
    return Settings.embedDecayTime;
  }

  private void setDeleteInvokeSetting(CommandEvent e, String change) {
    String proposedChange = change.toLowerCase();
    if (proposedChange.equals("true") || proposedChange.equals("false")) {
      deleteInvoke = Boolean.valueOf(proposedChange);
      e.getChannel().sendMessage("DeleteInvoke has been set to " + getDeleteInvoke()).queue();
    } else {
      e.getChannel().sendMessage("You must specify true or false.").queue();
    }
  }

  private void setEmbedDecaySetting(CommandEvent e, String change) {
    change = change.toLowerCase();
    if (change.equals("true") || change.equals("false")) {
      embedDecay = Boolean.valueOf(change);
      e.getChannel().sendMessage("EmbedDecay has been set to " + getEmbedDecay()).queue();
    } else {
      e.getChannel().sendMessage("You must specify true or false.").queue();
    }
  }

  private void setEmbedDecayTimeSetting(CommandEvent e, String change) {
    try {
      int proposedChange = Integer.parseInt(change);
      if (proposedChange >= 15 && proposedChange <= 120) {
        embedDecayTime = proposedChange;
        e.getChannel().sendMessage("EmbedDecayTime has been set to " + getEmbedDecayTime()).queue();
      } else {
        e.getChannel().sendMessage("You must provide a number between 15 - 120.").queue();
      }
    } catch (NumberFormatException error) {
      e.getChannel().sendMessage("You must provide a number between 15 - 120.").queue();
    }
  }

  public static void deleteInvoke(CommandEvent e) {
    if (deleteInvoke) {
      e.getMessage().delete().queue();
    }
  }

  public static void embedDecay(CommandEvent e, EmbedBuilder display) {
    if (embedDecay) {
      e.getChannel().sendMessage(display.build()).complete().delete().queueAfter(embedDecayTime, TimeUnit.SECONDS);
    } else {
      e.getChannel().sendMessage(display.build()).queue();
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
