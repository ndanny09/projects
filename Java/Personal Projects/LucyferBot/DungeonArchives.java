package promotion;

import java.time.Instant;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class DungeonArchives extends Command {
  public DungeonArchives() {
    this.name = "dungeonarchives";
    this.aliases = new String[] { "dungeonarchives, dainvite" };
    this.help = "Discord advertisement for Dungeon Archives";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    EmbedBuilder display = new EmbedBuilder();
    display.setTitle("__📜Dungeon Archives📜__");
    display.setDescription("📣Ark Mobile Documentation & Reference Server "
        + "\n```\"Knowledge is a treasure, but practice is the key to it.\"``` "
        + "\n⚔Dungeon \n🥚Breeding \n🛠Breeding \n🦖Taming "
        + "\n⚖Kibble Weight \n📈Player/Creature XP \n🎮How To Set Up An Unofficial Server "
        + "\n📱Emote Servers \n```Additional contributors welcome.```");
    display.addField("Discord:", "https://discord.gg/zw9jNCQ", false);
    display.setImage("https://cdn.discordapp.com/attachments/721939884235030558/758767568977788998/"
        + "BamMadeThisGifForDungeonArchivesIfYouSeeThisAnywhereElseItsStolen.gif");
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
