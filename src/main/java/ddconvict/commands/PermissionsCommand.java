package ddconvict.commands;

import java.util.ArrayList;


import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import ddconvict.database.*;
import net.dv8tion.jda.api.entities.Member;

/**
 * Provides list of users who have permissions to use the bot
 */

public class PermissionsCommand extends Command{
	
	public PermissionsCommand() {
		super.name = "permissions";
		super.aliases = new String[] {"permission"};
		super.help = "provides a list of users who can use bot functionality";
	}

	@Override
	protected void execute(CommandEvent event) {
		if (event.getAuthor().isBot()) {
			System.out.println("its bot!");
        		return;
        	}
		
		if (new ReadFromDB().getPermissionList().contains(event.getAuthor().getId())) {
			ArrayList<String> getNames = new ArrayList<>();
			for (String id: new ReadFromDB().getPermissionList()) {
				getNames.add(convertToName(id, event));
			}
			
			event.reply("People who can use bot: " + getNames.toString()
					.replace("[", "")
					.replace("]", ""));
		
		} else {
			event.reply("You don't have permissions to use this bot");
		}
	}
	
	private String convertToName(String userIdString, CommandEvent event) {
		String name = "";
		Guild guild = event.getGuild();
        	List<Member> users = guild.getMembers();
		
		for (Member mem : users)) {
			if (mem.getId().equalsIgnoreCase(userIdString)) {
				name = mem.getEffectiveName();
			}
		}
		return name;
	}
}
