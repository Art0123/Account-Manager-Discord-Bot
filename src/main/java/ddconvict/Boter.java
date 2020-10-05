package ddconvict;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import ddconvict.commands.*;
import ddconvict.database.CreateDBTable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Boter {
    private String token;
    private EventWaiter waiter;
	
	public Boter() {
		/**
		 * EventWaiter is needed only for 1 case(AddCommand class) 
		 * CreateDBTable makes 3 main database tables using H2 and JDBC
		 * Tables are: Registration, Commands, Permissions
		 * Commands table is created with default commands: add, remove, acc, list, permissions
		 */
		
		this.waiter = new EventWaiter();
        	new CreateDBTable();
        
		/** 
		 * initialize JDA builder, you can use other methods to initialize it
		 * but some methods I found were deprecated so I sticked with this one
		 * create your own TOKEN_HOLDER.txt, place it in project directory and insert
		 * your bot key into TOKEN_HOLDER.txt file
		 */
        
        	JDA jda = null;
        
		try {
			Scanner scanner = new Scanner(new File("TOKEN_HOLDER.txt"));
			this.token = scanner.nextLine();
			jda = JDABuilder.createDefault(this.token).setActivity(Activity.playing("with Accounts")).build().awaitReady();
		} catch (InterruptedException | LoginException | IOException e) {
			 e.printStackTrace();
		}
		 
        	// just to check if builder returned null for some unexpected reason
        	assert jda != null;
        
		/**
		 * JDA provides utilities library for CommandClientBuilder
		 * In this case it is used to merge separate commands into builder
		 * as well as it provides helping methods for easier commands implementations
		 */
        
		CommandClientBuilder builder = new CommandClientBuilder();
		builder.setPrefix("!"); // set up desired prefix for command
		builder.setOwnerId(Main.adminId);
		builder.addCommands(new AddCommand(waiter), new AccCommand(), new RemoveCommand(), new ListCommand(), new PermissionsCommand());
		
		CommandClient client = builder.build();	
		
		jda.addEventListener(client);
		jda.addEventListener(waiter);
	}

}
