
package miscellaneous;

import java.time.Instant;
import java.util.Random;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.EmbedBuilder;
import owner.Settings;

public class Bruh extends Command {
  public Bruh() {
    this.name = "bruh";
    this.aliases = new String[] { "bruh" };
    this.help = "Provides an out of context screenshot.";
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    EmbedBuilder display = new EmbedBuilder();
    display.setImage(randomLink(links));
    sendEmbed(e, display);
  }

  public static String randomLink(String[] links) {
    Random rand = new Random();
    int num = rand.nextInt(links.length);
    return links[num];
  }

  private void sendEmbed(CommandEvent e, EmbedBuilder display) {
    display.setColor(0x80000f);
    display.setFooter(e.getMember().getUser().getAsTag());
    display.setTimestamp(Instant.now());
    e.getChannel().sendTyping().queue();
    Settings.embedDecay(e, display);
  }

  private static String[] links = {
      "https://media.discordapp.net/attachments/745173342503567412/760977080774164480/image0.png?width=991&height=480",
      "https://media.discordapp.net/attachments/745173342503567412/760975441665720390/image0.png?width=887&height=480",
      "https://media.discordapp.net/attachments/557988186538639365/760977126932348998/image0.png",
      "https://media.discordapp.net/attachments/557988186538639365/751988626031640727/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/760621008977133588/image0.png?width=400&height=72",
      "https://media.discordapp.net/attachments/742868111660810355/760623055038447646/image0.jpg?width=400&height=89",
      "https://media.discordapp.net/attachments/742868111660810355/760623680874610738/image0.png?width=642&height=480",
      "https://media.discordapp.net/attachments/742868111660810355/760634585612288030/20200929_184951.jpg?width=400&height=83",
      "https://media.discordapp.net/attachments/742868111660810355/760634369290534963/20200929_184921.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/760634274079571988/20200929_184759.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/760634273928970240/20200929_184843.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/760635231950209024/20200929_185247.jpg?width=400&height=83",
      "https://media.discordapp.net/attachments/742868111660810355/760635151151005726/20200929_165611.jpg?width=400&height=112",
      "https://media.discordapp.net/attachments/742868111660810355/760644376367071282/image0.png?width=400&height=64",
      "https://media.discordapp.net/attachments/742868111660810355/760636202633527348/image0.png?width=400&height=93",
      "https://media.discordapp.net/attachments/742868111660810355/760652641696481310/image0.png?width=400&height=129",
      "https://media.discordapp.net/attachments/742868111660810355/760683914431168622/image0.jpg?width=400&height=76",
      "https://media.discordapp.net/attachments/742868111660810355/760684011583832094/image0.png?width=400&height=64",
      "https://media.discordapp.net/attachments/742868111660810355/760685400607555614/image0.jpg?width=400&height=50",
      "https://cdn.discordapp.com/attachments/742868111660810355/760890187231658044/image0.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/760250344680194068/image0.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/759830879777980426/image0.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/758462927816228894/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758464815663939584/20200923_185324.jpg?width=1026&height=214",
      "https://media.discordapp.net/attachments/742868111660810355/758464815471132692/20200923_185527.jpg?width=1026&height=310",
      "https://media.discordapp.net/attachments/742868111660810355/758464815252504576/20200923_190354.jpg?width=1026&height=319",
      "https://media.discordapp.net/attachments/742868111660810355/758464814972010546/20200923_190528.jpg?width=1026&height=233",
      "https://media.discordapp.net/attachments/742868111660810355/758464814740799538/20200923_190641.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758464814280081458/20200923_190751.jpg?width=1026&height=313",
      "https://media.discordapp.net/attachments/742868111660810355/758464962044624906/20200923_190852.jpg?width=496&height=480",
      "https://media.discordapp.net/attachments/742868111660810355/758465041548050442/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758466183543783464/image3.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758466183233142794/image1.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758466183065239582/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758466332101967872/image0-11.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758466614889938974/image0.jpg?width=1026&height=156",
      "https://media.discordapp.net/attachments/742868111660810355/758466949843517440/20200923_191640.jpg?width=1026&height=227",
      "https://media.discordapp.net/attachments/742868111660810355/758467198582128640/20200923_191745.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758467321404194816/image0.jpg?width=1026&height=91",
      "https://media.discordapp.net/attachments/742868111660810355/758467955042025472/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758468288015106099/20200923_192202.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758468332729925692/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758468344050876437/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/758468428214042665/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/758468554046701618/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758468928766214144/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758469006851833876/image0.jpg?width=1026&height=70",
      "https://media.discordapp.net/attachments/742868111660810355/758469311450578974/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758469383319453716/image0.jpg?width=1026&height=91",
      "https://media.discordapp.net/attachments/742868111660810355/758469473014382592/20200923_192640.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758469490907807834/image0.jpg?width=705&height=106",
      "https://media.discordapp.net/attachments/742868111660810355/758469734026051594/image0.jpg?width=705&height=126",
      "https://media.discordapp.net/attachments/742868111660810355/758469932768821298/image0.jpg?width=705&height=100",
      "https://media.discordapp.net/attachments/742868111660810355/758469987655745556/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758470051950231603/image0.jpg?width=705&height=82",
      "https://media.discordapp.net/attachments/742868111660810355/758470175170363452/image0.jpg?width=297&height=325",
      "https://media.discordapp.net/attachments/742868111660810355/758470307983261696/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758470348345180200/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758470372886052874/image0.jpg?width=705&height=125",
      "https://media.discordapp.net/attachments/742868111660810355/758470390082306078/image0.jpg?width=538&height=325",
      "https://media.discordapp.net/attachments/742868111660810355/758470457254346784/20200923_193024.jpg?width=642&height=325",
      "https://media.discordapp.net/attachments/742868111660810355/758470594814935050/image0.jpg?width=705&height=69",
      "https://media.discordapp.net/attachments/742868111660810355/758470622757519391/image0.jpg?width=705&height=59",
      "https://media.discordapp.net/attachments/742868111660810355/758470677073756170/image0.jpg?width=705&height=140",
      "https://media.discordapp.net/attachments/742868111660810355/758470739182485534/image0.jpg?width=705&height=113",
      "https://media.discordapp.net/attachments/742868111660810355/758470840123129866/image0.jpg?width=705&height=115",
      "https://media.discordapp.net/attachments/742868111660810355/758470862352810004/image0.jpg?width=705&height=115",
      "https://media.discordapp.net/attachments/742868111660810355/758470895605514304/image0.png?width=216&height=325",
      "https://media.discordapp.net/attachments/742868111660810355/758471000227577907/20200923_193244.jpg?width=705&height=176",
      "https://media.discordapp.net/attachments/742868111660810355/758471231363088414/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758471284358905906/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758471284853833738/20200923_193358.jpg?width=705&height=148",
      "https://media.discordapp.net/attachments/742868111660810355/758471342830911528/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758471480827969566/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758471747162603530/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/758471779156754462/20200923_193550.jpg?width=705&height=256",
      "https://media.discordapp.net/attachments/742868111660810355/758471807690473472/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758471402168254495/image0.jpg?width=705&height=116",
      "https://media.discordapp.net/attachments/742868111660810355/758471912581365770/image0.jpg?width=445&height=325",
      "https://media.discordapp.net/attachments/742868111660810355/758472013236142110/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758472142487552020/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758472180803305493/20200923_193728.jpg?width=365&height=325",
      "https://media.discordapp.net/attachments/742868111660810355/758472311522590720/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758472415633473586/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758472511213535242/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758472603773304862/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758472668063858708/image0.jpg?width=705&height=172",
      "https://media.discordapp.net/attachments/742868111660810355/758472722648662056/image0.jpg?width=705&height=101",
      "https://media.discordapp.net/attachments/742868111660810355/758472819704463390/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758472603773304862/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758473105739612190/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758473238048931910/image0.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/758473496904335390/20200923_194245.jpg?width=705&height=236",
      "https://media.discordapp.net/attachments/742868111660810355/758473999000010832/image0.jpg",
      "https://cdn.discordapp.com/attachments/745173342503567412/758563441236508703/image0.png",
      "https://media.discordapp.net/attachments/745173342503567412/758693996142133278/image0.jpg?width=400&height=83",
      "https://media.discordapp.net/attachments/745173342503567412/758660905050832906/image0.png?width=400&height=76",
      "https://cdn.discordapp.com/attachments/742868111660810355/758552191849070612/image0.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/758786470327615548/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758789949931257876/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758790266722320394/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758790299073249284/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758790406002835476/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758790683284340756/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758790817744945222/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758790995147096069/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758791139544530954/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758791461004771348/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758791605703409664/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758791629015351330/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758792309013217300/image0.jpg",
      "https://cdn.discordapp.com/attachments/742868111660810355/758837679881191464/image0.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/759450108939993098/image0.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/759474272829308928/image0.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/759475350782345226/image1.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/759475351520411678/image2.png",
      "https://cdn.discordapp.com/attachments/742868111660810355/759510234343145492/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/759521566215110746/image0.jpg?width=830&height=480",
      "https://cdn.discordapp.com/attachments/742868111660810355/777714381630078996/20201115_205901.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/777702798916976650/image0.png?width=400&height=67",
      "https://media.discordapp.net/attachments/742868111660810355/777319373610287104/20201114_184915.jpg?width=400&height=93",
      "https://cdn.discordapp.com/attachments/742868111660810355/777295881186836500/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/775549197460963328/20201109_213433.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/775549197650493460/20201109_213414.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/775549197793493022/20201109_211749.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/774867604305215508/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/771431631721332786/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/774716083822133248/Screenshot_2020-11-07_112355.png",
      "https://media.discordapp.net/attachments/742868111660810355/774716083822133248/Screenshot_2020-11-07_112355.png",
      "https://media.discordapp.net/attachments/742868111660810355/774241873098571776/Screen_Shot_2020-11-06_at_7.00.35_AM.png",
      "https://media.discordapp.net/attachments/745482998039969883/773672586575347752/EmAv9SOU4AEy-_x.png",
      "https://media.discordapp.net/attachments/742868111660810355/773651290781384734/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/773656253440196608/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/773656253627891742/image1.png",
      "https://media.discordapp.net/attachments/742868111660810355/773656253783867392/image2.png",
      "https://media.discordapp.net/attachments/742868111660810355/773656253993320478/image3.png",
      "https://media.discordapp.net/attachments/742868111660810355/773647813010522112/9tF9zQZEIOEAAAAASUVORK5CYII.png",
      "https://media.discordapp.net/attachments/742868111660810355/773643779370516500/iLFn29Rj0ysAAAAASUVORK5CYII.png",
      "https://media.discordapp.net/attachments/742868111660810355/773640818077990942/Screenshot_20201104-151215_Discord.png?width=1026&height=420",
      "https://media.discordapp.net/attachments/742868111660810355/773628593006706718/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/773595718324584469/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/773311228486746142/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/773294092477267968/image0.jpg?width=680&height=481",
      "https://media.discordapp.net/attachments/742868111660810355/772654811593965578/Screenshot_20201101-215415_Discord.png",
      "https://media.discordapp.net/attachments/742868111660810355/772653439536201758/20201101_214847.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/772653147272380427/20201101_214655.jpg",
      "https://media.discordapp.net/attachments/742868111660810355/772646487083450398/image0.png",
      "https://media.discordapp.net/attachments/742868111660810355/772646487083450398/image0.png",
      "https://media.discordapp.net/attachments/707068212512292916/771506097931550771/Screen_Shot_2020-10-29_at_15.49.33.png",
      "https://media.discordapp.net/attachments/707068212512292916/771507541380300830/20201029_185238.jpg",
      "https://media.discordapp.net/attachments/707068212512292916/771507541758836767/20201029_185331.jpg",
      "https://media.discordapp.net/attachments/758776344472584203/771509450506108969/Screen_Shot_2020-10-29_at_7.02.43_PM.png",
      "https://images-ext-1.discordapp.net/external/t3Gj3nQ1J62CORUDwHDs99Cg_84bzMHTi_orYQa0hu4/https/media.discordapp.net/attachments/764315294788223016/771415895485710336/image0.png",
      "https://media.discordapp.net/attachments/758776344472584203/773941528543625217/image0.png" };
}
