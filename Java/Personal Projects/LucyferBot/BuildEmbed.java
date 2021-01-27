package owner;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class BuildEmbed extends Command {
  EventWaiter waiter;
  private int fieldAmount;
  private int fieldsAdded;

  public BuildEmbed(EventWaiter waiter) {
    this.name = "BuildEmbed";
    this.aliases = new String[] { "buildembed", "embed", "embedtemplate" };
    this.arguments = "[1]9 Character Switch";
    this.help = "Builds embeds using the Discord message line.";
    this.ownerCommand = true;
    this.waiter = waiter;
  }

  @Override
  protected void execute(CommandEvent e) {
    Settings.deleteInvoke(e);
    String[] args = e.getMessage().getContentRaw().split("\\s");
    int arguments = args.length;
    if (arguments == 1) { // Character Switch Reminder
      e.getChannel().sendMessage("Refer to the help documentation of BuildEmbed for more details."
          + "\n1-Title | 2-Author | 3-Description | 4-Fields | 5-Thumbnail | 6-Image | 7-Color | 8-Footer | 9-Timestamp")
          .complete().delete().queueAfter(30, TimeUnit.SECONDS);
    } else if (arguments == 2) { // Builds Embed
      char[] ca = args[1].toCharArray();
      if (ca.length == 9) {
        EmbedBuilder display = new EmbedBuilder();
        containsTitle(e, waiter, display, ca);
      }
    }
  }

  private void containsTitle(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    fieldsAdded = 0;
    if (ca[0] == 'T') {
      e.getChannel().sendMessage("__**Title:**__ \n1 - *Title* [text] \n2 - *Title (Hyperlink)* [text>> url]")
          .complete().delete().queueAfter(30, TimeUnit.SECONDS);
      waiter.waitForEvent(GuildMessageReceivedEvent.class,
          w -> w.getAuthor().equals(e.getAuthor()) && w.getChannel().equals(e.getChannel()), w -> {
            w.getMessage().delete().queue();
            String[] args = w.getMessage().getContentRaw().split(">> ");
            int arguments = args.length;
            if (arguments == 1) { // Title [text]
              display.setTitle(args[0]);
              containsAuthor(e, waiter, display, ca);
            } else if (arguments == 2) { // Title (Hyperlink) [text>> url]
              try {
                display.setTitle(args[0], args[1]);
                containsAuthor(e, waiter, display, ca);
              } catch (IllegalArgumentException error) { // Invalid URL
                e.getChannel()
                    .sendMessage("Argument 2 must be a valid URL. "
                        + "Type \"yes\" to continue, anything else otherwise to cancel.")
                    .complete().delete().queueAfter(15, TimeUnit.SECONDS);
                waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()),
                    wInv -> {
                      wInv.getMessage().delete().queue();
                      String again = wInv.getMessage().getContentRaw();
                      if (again.equalsIgnoreCase("yes")) {
                        containsTitle(e, waiter, display, ca);
                      } else {
                      }
                    }, 15, TimeUnit.SECONDS, () -> {
                    });
              }
            } else { // Invalid Arguments
              e.getChannel()
                  .sendMessage(
                      "Invalid number of arguments. Type \"yes\" to continue, anything else otherwise to cancel.")
                  .complete().delete().queueAfter(15, TimeUnit.SECONDS);
              waiter.waitForEvent(GuildMessageReceivedEvent.class,
                  wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()), wInv -> {
                    wInv.getMessage().delete().queue();
                    String again = wInv.getMessage().getContentRaw();
                    if (again.equalsIgnoreCase("yes")) {
                      containsTitle(e, waiter, display, ca);
                    } else {
                    }
                  }, 15, TimeUnit.SECONDS, () -> {
                  });
            }
          }, 1, TimeUnit.MINUTES, () -> {
          });
    } else if (ca[0] == 'F') {
      containsAuthor(e, waiter, display, ca);
    }
  }

  private void containsAuthor(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (ca[1] == 'T') {
      e.getChannel().sendMessage(
          "__**Author:**__ \n1 - *Author* [text] \n2 - *Author(Hyperlink)* [text>> url] \n3 - *Author(Hyperlink) & Icon* [text>> url>> iconUrl]")
          .complete().delete().queueAfter(30, TimeUnit.SECONDS);
      waiter.waitForEvent(GuildMessageReceivedEvent.class,
          w -> w.getAuthor().equals(e.getAuthor()) && w.getChannel().equals(e.getChannel()), w -> {
            w.getMessage().delete().queue();
            String[] args = w.getMessage().getContentRaw().split(">> ");
            int arguments = args.length;
            if (arguments == 1) { // Author [text]
              display.setAuthor(args[0]);
              containsDescription(e, waiter, display, ca);
            } else if (arguments == 2) { // Author(Hyperlink) [text>> url]
              try {
                display.setAuthor(args[0], args[1]);
                containsDescription(e, waiter, display, ca);
              } catch (IllegalArgumentException error) { // Invalid URL
                e.getChannel()
                    .sendMessage(
                        "Argument 2 must be a valid URL. Type \"yes\" to continue, anything else otherwise to cancel.")
                    .complete().delete().queueAfter(15, TimeUnit.SECONDS);
                waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()),
                    wInv -> {
                      wInv.getMessage().delete().queue();
                      String again = wInv.getMessage().getContentRaw();
                      if (again.equalsIgnoreCase("yes")) {
                        containsAuthor(e, waiter, display, ca);
                      } else {
                      }
                    }, 15, TimeUnit.SECONDS, () -> {
                    });
              }
            } else if (arguments == 3) { // Author(Hyperlink) & Icon [text>> url>> iconUrl]
              try {
                display.setAuthor(args[0], args[1], args[2]);
                containsDescription(e, waiter, display, ca);
              } catch (IllegalArgumentException error) { // Invalid URL
                e.getChannel().sendMessage(
                    "Arguments 2 & 3 must be a valid URL. Type \"yes\" to continue, anything else otherwise to cancel.")
                    .complete().delete().queueAfter(15, TimeUnit.SECONDS);
                waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()),
                    wInv -> {
                      wInv.getMessage().delete().queue();
                      String again = wInv.getMessage().getContentRaw();
                      if (again.equalsIgnoreCase("yes")) {
                        containsAuthor(e, waiter, display, ca);
                      } else {
                      }
                    }, 15, TimeUnit.SECONDS, () -> {
                    });
              }
            } else { // Invalid Arguments
              e.getChannel()
                  .sendMessage(
                      "Invalid number of arguments. Type \"yes\" to continue, anything else otherwise to cancel.")
                  .complete().delete().queueAfter(15, TimeUnit.SECONDS);
              waiter.waitForEvent(GuildMessageReceivedEvent.class,
                  wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()), wInv -> {
                    wInv.getMessage().delete().queue();
                    String again = wInv.getMessage().getContentRaw();
                    if (again.equalsIgnoreCase("yes")) {
                      containsAuthor(e, waiter, display, ca);
                    } else {
                    }
                  }, 15, TimeUnit.SECONDS, () -> {
                  });
            }
          }, 1, TimeUnit.MINUTES, () -> {
          });
    } else if (ca[1] == 'F') {
      containsDescription(e, waiter, display, ca);
    }
  }

  private void containsDescription(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (ca[2] == 'T') {
      e.getChannel().sendMessage("__**Description:**__ \n[text]").complete().delete().queueAfter(30, TimeUnit.SECONDS);
      waiter.waitForEvent(GuildMessageReceivedEvent.class,
          w -> w.getAuthor().equals(e.getAuthor()) && w.getChannel().equals(e.getChannel()), w -> {
            w.getMessage().delete().queue();
            display.setDescription(w.getMessage().getContentRaw());
            containsField(e, waiter, display, ca);
          }, 1, TimeUnit.MINUTES, () -> {
          });
    } else if (ca[2] == 'F') {
      containsField(e, waiter, display, ca);
    }
  }

  private void containsField(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (ca[3] == 'T') {
      e.getChannel().sendMessage("__**Fields:**__ \n[0-8]").complete().delete().queueAfter(30, TimeUnit.SECONDS);
      waiter.waitForEvent(GuildMessageReceivedEvent.class,
          w -> w.getAuthor().equals(e.getAuthor()) && w.getChannel().equals(e.getChannel()), w -> {
            w.getMessage().delete().queue();
            try { // Fields (0-8)
              fieldAmount = Integer.parseInt(w.getMessage().getContentRaw());
              if (fieldAmount > 0 && fieldAmount <= 8) { // Valid Range
                addField(e, waiter, display, ca);
              } else if (fieldAmount == 0) { // No Fields
                containsThumbnail(e, waiter, display, ca);
              } else { // Invalid Range
                e.getChannel()
                    .sendMessage(
                        "You must provide a valid number. Type \"yes\" to continue, anything else otherwise to cancel.")
                    .complete().delete().queueAfter(15, TimeUnit.SECONDS);
                waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()),
                    wInv -> {
                      wInv.getMessage().delete().queue();
                      String again = wInv.getMessage().getContentRaw();
                      if (again.equalsIgnoreCase("yes")) {
                        containsField(e, waiter, display, ca);
                      } else {
                      }
                    }, 15, TimeUnit.SECONDS, () -> {
                    });
              }
            } catch (NumberFormatException error) { // Invalid Number
              e.getChannel().sendMessage("Invalid number. Type \"yes\" to continue, anything else otherwise to cancel.")
                  .complete().delete().queueAfter(15, TimeUnit.SECONDS);
              waiter.waitForEvent(GuildMessageReceivedEvent.class,
                  wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()), wInv -> {
                    wInv.getMessage().delete().queue();
                    String again = wInv.getMessage().getContentRaw();
                    if (again.equalsIgnoreCase("yes")) {
                      containsField(e, waiter, display, ca);
                    } else {
                    }
                  }, 15, TimeUnit.SECONDS, () -> {
                  });
            }
          }, 1, TimeUnit.MINUTES, () -> {
          });
    } else if (ca[3] == 'F') {
      containsThumbnail(e, waiter, display, ca);
    }
  }

  private void addField(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (fieldAmount > fieldsAdded) { // Loop Field Quantity
      e.getChannel().sendMessage("__**Field (" + (fieldsAdded + 1) + "):**__ \n[text>> text>> inline]").complete()
          .delete().queueAfter(30, TimeUnit.SECONDS);
      waiter.waitForEvent(GuildMessageReceivedEvent.class,
          w -> w.getAuthor().equals(e.getAuthor()) && w.getChannel().equals(e.getChannel()), w -> {
            w.getMessage().delete().queue();
            String[] args = w.getMessage().getContentRaw().split(">> ");
            int arguments = args.length;
            if (arguments == 3) { // [text>> text>> inline]
              if (args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")) {
                display.addField(args[0], args[1], Boolean.parseBoolean(args[2]));
                fieldsAdded++;
                addField(e, waiter, display, ca);
              } else {
                e.getChannel().sendMessage(
                    "Argument 3 must be \"true\" or \"false\". Type \"yes\" to continue, anything else otherwise to cancel.")
                    .complete().delete().queueAfter(15, TimeUnit.SECONDS);
                waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()),
                    wInv -> {
                      wInv.getMessage().delete().queue();
                      String again = wInv.getMessage().getContentRaw();
                      if (again.equalsIgnoreCase("yes")) {
                        addField(e, waiter, display, ca);
                      } else {
                      }
                    }, 15, TimeUnit.SECONDS, () -> {
                    });
              }
            } else { // Invalid Arguments
              e.getChannel()
                  .sendMessage(
                      "Invalid number of arguments. Type \"yes\" to continue, anything else otherwise to cancel.")
                  .complete().delete().queueAfter(15, TimeUnit.SECONDS);
              waiter.waitForEvent(GuildMessageReceivedEvent.class,
                  wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()), wInv -> {
                    wInv.getMessage().delete().queue();
                    String again = wInv.getMessage().getContentRaw();
                    if (again.equalsIgnoreCase("yes")) {
                      addField(e, waiter, display, ca);
                    } else {
                    }
                  }, 15, TimeUnit.SECONDS, () -> {
                  });
            }
          }, 1, TimeUnit.MINUTES, () -> {
          });
    } else {
      containsThumbnail(e, waiter, display, ca);
    }
  }

  private void containsThumbnail(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (ca[4] == 'T') {
      e.getChannel().sendMessage("__**Thumbnail:**__ \n[url]").complete().delete().queueAfter(30, TimeUnit.SECONDS);
      waiter.waitForEvent(GuildMessageReceivedEvent.class,
          w -> w.getAuthor().equals(e.getAuthor()) && w.getChannel().equals(e.getChannel()), w -> { //
            w.getMessage().delete().queue();
            try { // [url]
              display.setThumbnail(w.getMessage().getContentRaw());
              containsImage(e, waiter, display, ca);
            } catch (IllegalArgumentException error) { // Invalid URL
              e.getChannel()
                  .sendMessage("Must be a valid URL. Type \"yes\" to continue, anything else otherwise to cancel.")
                  .complete().delete().queueAfter(15, TimeUnit.SECONDS);
              waiter.waitForEvent(GuildMessageReceivedEvent.class,
                  wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()), wInv -> {
                    wInv.getMessage().delete().queue();
                    String again = wInv.getMessage().getContentRaw();
                    if (again.equalsIgnoreCase("yes")) {
                      containsThumbnail(e, waiter, display, ca);
                    } else {
                    }
                  }, 15, TimeUnit.SECONDS, () -> {
                  });
            }
          }, 1, TimeUnit.MINUTES, () -> {
          });
    } else if (ca[4] == 'F')

    {
      containsImage(e, waiter, display, ca);
    }
  }

  private void containsImage(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (ca[5] == 'T') {
      e.getChannel().sendMessage("__**Image:**__ \n[url]").complete().delete().queueAfter(30, TimeUnit.SECONDS);
      waiter.waitForEvent(GuildMessageReceivedEvent.class,
          w -> w.getAuthor().equals(e.getAuthor()) && w.getChannel().equals(e.getChannel()), w -> {
            w.getMessage().delete().queue();
            try { // [url]
              display.setImage(w.getMessage().getContentRaw());
              containsColor(e, waiter, display, ca);
            } catch (IllegalArgumentException error) { // Invalid URL
              e.getChannel()
                  .sendMessage("Must be a valid URL. Type \"yes\" to continue, anything else otherwise to cancel.")
                  .complete().delete().queueAfter(15, TimeUnit.SECONDS);
              waiter.waitForEvent(GuildMessageReceivedEvent.class,
                  wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()), wInv -> {
                    wInv.getMessage().delete().queue();
                    String again = wInv.getMessage().getContentRaw();
                    if (again.equalsIgnoreCase("yes")) {
                      containsImage(e, waiter, display, ca);
                    } else {
                    }
                  }, 15, TimeUnit.SECONDS, () -> {
                  });
            }
          }, 1, TimeUnit.MINUTES, () -> {
          });
    } else if (ca[5] == 'F') {
      containsColor(e, waiter, display, ca);
    }
  }

  private void containsColor(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (ca[6] == 'T') {
      e.getChannel().sendMessage("__**Color:**__ Automatically applied.").complete().delete().queueAfter(30,
          TimeUnit.SECONDS);
      display.setColor(0x80000f);
      containsFooter(e, waiter, display, ca);
    } else if (ca[6] == 'F') {
      containsFooter(e, waiter, display, ca);
    }
  }

  private void containsFooter(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (ca[7] == 'T') {
      e.getChannel().sendMessage("__**Footer**__ \n1 - *Footer* [text] \n2 - *Footer (Hyperlink)* [text>> iconUrl]")
          .complete().delete().queueAfter(30, TimeUnit.SECONDS);
      waiter.waitForEvent(GuildMessageReceivedEvent.class,
          w -> w.getAuthor().equals(e.getAuthor()) && w.getChannel().equals(e.getChannel()), w -> {
            w.getMessage().delete().queue();
            String[] args = w.getMessage().getContentRaw().split(">> ");
            int arguments = args.length;
            if (arguments == 1) {
              display.setFooter(args[0]);
              containsTimestamp(e, waiter, display, ca);
            } else if (arguments == 2) {
              try { // [text>> iconUrl]
                display.setFooter(args[0], args[1]);
                containsTimestamp(e, waiter, display, ca);
              } catch (IllegalArgumentException error) { // Invalid URL
                e.getChannel()
                    .sendMessage(
                        "Argument 2 be a valid URL. Type \"yes\" to continue, anything else otherwise to cancel.")
                    .complete().delete().queueAfter(15, TimeUnit.SECONDS);
                waiter.waitForEvent(GuildMessageReceivedEvent.class,
                    wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()),
                    wInv -> {
                      wInv.getMessage().delete().queue();
                      String again = wInv.getMessage().getContentRaw();
                      if (again.equalsIgnoreCase("yes")) {
                        containsFooter(e, waiter, display, ca);
                      } else {
                      }
                    }, 15, TimeUnit.SECONDS, () -> {
                    });
              }
            } else { // Invalid Arguments
              e.getChannel().sendMessage(
                  "Invalid number of arguments. Type \"yes\" to continue, anything else otherwise to cancel.");
              waiter.waitForEvent(GuildMessageReceivedEvent.class,
                  wInv -> wInv.getAuthor().equals(e.getAuthor()) && wInv.getChannel().equals(e.getChannel()), wInv -> {
                    wInv.getMessage().delete().queue();
                    String again = wInv.getMessage().getContentRaw();
                    if (again.equalsIgnoreCase("yes")) {
                      containsFooter(e, waiter, display, ca);
                    } else {
                    }
                  }, 15, TimeUnit.SECONDS, () -> {
                  });
            }
          }, 1, TimeUnit.MINUTES, () -> {
          });
    } else if (ca[7] == 'F') {
      containsTimestamp(e, waiter, display, ca);
    }
  }

  private void containsTimestamp(CommandEvent e, EventWaiter waiter, EmbedBuilder display, char[] ca) {
    if (ca[8] == 'T') {
      e.getChannel().sendMessage("__**Timestamp:**__ Automatically applied.");
      display.setTimestamp(Instant.now());
    }
    e.getChannel().sendMessage(display.build()).queue();
  }
}