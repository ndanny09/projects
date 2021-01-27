package lucyfer;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import about.Credits;
import about.Help;
import about.Info;
import games.Choice;
import games.Flip;
import games.HighOrLow;
import games.Roll;
import miscellaneous.Bruh;
import miscellaneous.Echo;
import miscellaneous.Ping;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import owner.BuildEmbed;
import owner.Clear;
import owner.Settings;
import owner.Shutdown;
import promotion.DungeonArchives;
import utility.Avatar;
import utility.Emote;
import utility.Remind;
import utility.ServerInfo;
import utility.WhoIs;

public class Main {
  private static JDA api;
  private static String botToken = "";

  public static void main(String[] args) {
    // Attempt to login
    try {
      api = JDABuilder.createDefault(botToken).build().awaitReady();
      System.out.println("[" + api.getSelfUser().getName() + "#" + api.getSelfUser().getDiscriminator() + "] is online.");
    } catch (LoginException | InterruptedException e) {
      e.printStackTrace();
    }

    // Online Status
    api.getPresence().setStatus(OnlineStatus.ONLINE);
    api.getPresence().setActivity(Activity.watching("The time pass by."));

    // Event Waiter
    EventWaiter waiter = new EventWaiter();
    
    // Command Manager
    CommandClientBuilder commands = new CommandClientBuilder();
    commands.setOwnerId("204448598539239424");
    commands.addCommands(new Avatar(), new Bruh(), new Choice(), new Clear(), new Credits(), new DungeonArchives(),
        new Echo(), new Emote(), new Flip(), new Help(), new HighOrLow(waiter), new Info(), new Ping(), new Remind(),
        new Roll(), new ServerInfo(), new Settings(), new Shutdown(), new WhoIs(), new BuildEmbed(waiter));
    commands.setPrefix("?");
    commands.setAlternativePrefix("L:");
    commands.setHelpWord("commands");
    CommandClient client = commands.build();
    //api.addEventListener(waiter, client, new MessageLog()); By default, this command is off since it is only used for debugging.
  }
}
