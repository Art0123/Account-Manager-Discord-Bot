package ddconvict.commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import ddconvict.database.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * This class adds account to database, or overwrites already existing account
 * also here you can add permissions to users, replace my discord id with yours
 * where comment is // insert your id
 */

public class AddCommand extends Command {
	private EventWaiter waiter;
	
	public AddCommand(EventWaiter waiter) {
		this.waiter = waiter;
		super.name = "add";
		super.help = "adds account to database - example: !add mage1 login password \n"
				+ "if account already exists [checks profession, in this case mage1] -> the bot will ask if you want to update login/password";
	}

	@Override
	protected void execute(CommandEvent event) {
		if (event.getAuthor().isBot()) {
			System.out.println("its bot!");
        	return;
        }
		
		String[] userTyped = event.getMessage().getContentRaw().split("\\s+");
		
		if (new ReadFromDB().getPermissionList().contains(event.getAuthor().getId())) {
			if (userTyped[1].equalsIgnoreCase("permissions") && !userTyped[2].isBlank() && event.getAuthor().getId().equals("194088158206361600")) { // insert your id
				if (new ReadFromDB().getPermissionList().contains(userTyped[2])) {
					event.reply("User <@" + userTyped[2] + "> already has permissions");
				} else {
					new InsertIntoDB().insertPermissionIntoDB(userTyped[2]);
					event.reply("Permissions added for user <@" + userTyped[2] + ">");
				}
				
			} else if (userTyped[1].equalsIgnoreCase("permission") && !userTyped[2].isBlank() && !event.getAuthor().getId().equals("194088158206361600")) { // insert your id
				event.reply("You don't have such permissions");
				
			} else if (userTyped[1].equalsIgnoreCase("add") 
					|| userTyped[1].equalsIgnoreCase("remove") 
					|| userTyped[1].equalsIgnoreCase("commands") 
					|| userTyped[1].equalsIgnoreCase("acc")
					|| userTyped[1].equalsIgnoreCase("list")
					|| userTyped[1].equalsIgnoreCase("help")) {
				event.reply("Main commands cannot be added as account profession");
				
			} else {
				if (userTyped.length < 4) {
					event.reply("You need to provide profession, login and password, check if anything is missing");
				} else {
					String prof = userTyped[1];
	            	String login = userTyped[2];
	            	String pasw = userTyped[3];
	            	
	            	if (new ReadFromDB().getAccounts().toString().contains(prof)) {
	            		event.reply("Account " + userTyped[1] + " already exists \nDo you want to update already existing account? (type y/n)");
	            		
	            		waiter.waitForEvent(GuildMessageReceivedEvent.class, e -> e.getAuthor().equals(event.getAuthor())
	            				&& e.getChannel().equals(event.getChannel()), e -> {
	            					String answer = e.getMessage().getContentRaw();
	            					
	            					if (answer.equalsIgnoreCase("y")) {
	            						new UpdateDBTable(prof, login, pasw);
	            						event.reply("Account " + prof + " has been modified by " + e.getAuthor().getAsMention());
	            					} else if (answer.equalsIgnoreCase("n")) {
	            						event.reply("Nothing changed");
	            					}
	            					
	            				}, 10, TimeUnit.SECONDS, () -> event.reply("you didnt answer"));
	            	
				
            	} else {
            		new InsertIntoDB().insertAccountIntoDB(prof, login, pasw);
                	event.reply("Account " + userTyped[1] + " is added");
                	new InsertIntoDB().insertCommandIntoDB(prof); 
            		}
				}	
			}
		} else {
			event.reply("You don't have permissions to use this bot");
		}
	}
}
