package about;

import java.time.Instant;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class Credits extends Command {
  public Credits() {
    this.name = "credits";
    this.aliases = new String[] { "credits", "thankyou" };
    this.help = "Provides user with a list of credits for the bot.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    EmbedBuilder display = new EmbedBuilder();
    display.setTitle("__Credits__");
    display.setDescription("My thanks to the following people for their assistance in the bot's development.");
    display.addField("**APIs, Libraries:**", "JDA, JDA-Utilities", false);
    display.addField("**Resources:**", "techtoolbox, MenuDocs, Kody Simpson", false);
    display.addField("**Testers:**", "<@517468040695382023>, <@538957870520205313>, <@605808637163601921>, "
        + "<@432248847746727936>, <@546401769857482901>, <@199530973023567872>, <@165863726447067147>, <@331961073513005059>"
        + "<@690041327278358547>, <@194452924552708096>, <@435279003142586390>, <@535110724712923171>", false);
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
