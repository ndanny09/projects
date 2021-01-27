package utility;

import java.time.Instant;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class Remind extends Command {
  public Remind() {
    this.name = "remind";
    this.aliases = new String[] { "remind", "reminder", "remindme", "notify", "mentionme", "alert" };
    this.arguments = "[1]TimeDuration&TimeType/Time [2]TimeDuration/TimeType/EventName [3++]EventName";
    this.help = "Sets a timer and alerts the user when the time expires.";
    this.ownerCommand = true;
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    String timerName = "";
    if (timeTypeInFirstArgument(args)) { // timeType in first argument
      try {
        String timeString = args[1].substring(0, args[1].length() - 1);
        int timeDuration = Integer.parseInt(timeString);
        char timeType = args[1].charAt(args[1].length() - 1);
        if (timeDurationTimeTypeValidLimit(timeDuration, timeType)) {
          if (arguments > 2) { // Timer Name
            for (int i = 2; i < args.length; i++) {
              timerName += args[i] + " ";
            }
          }
          setReminder(e, timeDuration, timeType, timerName);
          setTimer(e, timeDuration, timeType, timerName);
        } else {
          e.getChannel().sendMessage("You can only set the timer for the maximum length of a day.").queue();
        }
      } catch (NumberFormatException error) {
        e.getChannel().sendMessage("You must to specify a valid numerical value, followed by an accepted time type.")
            .queue();
      }
    } else { // timeType not in first argument
      try {
        int timeDuration = Integer.parseInt(args[1]);
        char timeType = timeTypeStringConversion(args);
        if (timeType == 's' || timeType == 'm' || timeType == 'h') {
          if (timeDurationTimeTypeValidLimit(timeDuration, timeType)) {
            if (arguments > 3) { // Timer Name
              for (int i = 3; i < args.length; i++) {
                timerName += args[i] + " ";
              }
            }
            setReminder(e, timeDuration, timeType, timerName);
            setTimer(e, timeDuration, timeType, timerName);
          } else {
            e.getChannel().sendMessage("You can only set the timer for the maximum length of a day.").queue();
          }
        } else {
          e.getChannel().sendMessage("You must to specify a valid numerical value, followed by an accepted time type.")
              .queue();
        }
      } catch (NumberFormatException error) {
        e.getChannel().sendMessage("You must to specify a valid numerical value, followed by an accepted time type.")
            .queue();
      }
    }
  }

  private boolean timeTypeInFirstArgument(String[] args) {
    char timeType = args[1].charAt(args[1].length() - 1);
    if (timeType == 's' || timeType == 'm' || timeType == 'h') {
      return true;
    }
    return false;
  }

  private boolean timeDurationTimeTypeValidLimit(int timeDuration, char timeType) {
    if (timeType == 'h' && timeDuration >= 0 && timeDuration <= 24) {
      return true;
    } else if (timeType == 'm' && timeDuration >= 0 && timeDuration <= 1440) {
      return true;
    } else if (timeType == 's' && timeDuration >= 0 && timeDuration <= 86400) {
      return true;
    }
    return false;
  }

  private String getTimeTypeString(char timeType) {
    if (timeType == 's') {
      return "seconds";
    } else if (timeType == 'm') {
      return "minutes";
    } else if (timeType == 'h') {
      return "hours";
    }
    return null;
  }

  private int timeDurationIntoMilliseconds(int timeDuration, char timeType) {
    if (timeType == 's') {
      return timeDuration * 1000;
    } else if (timeType == 'm') {
      return timeDuration * 60000;
    } else if (timeType == 'h') {
      return timeDuration * 3600000;
    }
    return -1;
  }

  private void setReminder(CommandEvent e, int timeDuration, char timeType, String timerName) {
    EmbedBuilder display = new EmbedBuilder();
    display.setTitle("__Reminder__");
    display.setDescription(!timerName.equals("")
        ? "Time set for " + timerName + " in (" + timeDuration + ") " + getTimeTypeString(timeType) + "."
        : "Timer set to mention you in (" + timeDuration + ") " + getTimeTypeString(timeType) + ".");
    sendEmbed(e, display);
  }

  public void setTimer(CommandEvent e, int timeDuration, char timeType, String timerName) {
    new java.util.Timer().schedule(new java.util.TimerTask() {
      public void run() {
        EmbedBuilder display = new EmbedBuilder();
        display.setTitle("__Reminder__");
        display.setDescription(
            !timerName.equals("") ? "Hey " + e.getMember().getAsMention() + ", " + timerName + " is starting now!"
                : "Hey " + e.getMember().getAsMention() + ", time's up!");
        sendEmbed(e, display);
      }
    }, timeDurationIntoMilliseconds(timeDuration, timeType));
  }

  private char timeTypeStringConversion(String[] args) {
    if (args[2].equals("hours") || args[2].equals("hour") || args[2].equals("hrs") || args[2].equals("hr")
        || args[2].equals("h")) {
      return 'h';
    } else if (args[2].equals("minutes") || args[2].equals("minute") || args[2].equals("mins") || args[2].equals("min")
        || args[2].equals("m")) {
      return 'm';
    } else if (args[2].equals("seconds") || args[2].equals("second") || args[2].equals("secs") || args[2].equals("sec")
        || args[2].equals("s")) {
      return 's';
    }
    return args[1].charAt(args[1].length() - 1);
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }
}