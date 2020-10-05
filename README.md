# Account-Manager-Discord-Bot
This bot was made with intent to store some game accounts in a database and be able to retrieve it.  
Mostly it is used when playing with groups of people, as everyone creates different accounts - this
bot allows to store this information into H2 database with JDBC and anyone with permissions can access all accounts list from discord client.  
With this bot, you won't need to constantly update your text files with new account information and then
distribute that text file to all group members. Anyone with permissions can add/retrieve/remove accounts information.
# References
Bot is built with the help of [JDA](https://github.com/DV8FromTheWorld/JDA)  
[H2](http://www.h2database.com/html/cheatSheet.html) database with Server Mode and JDBC was used to store data in a local repository  
Additionally, [JDA Utilities](https://github.com/JDA-Applications/JDA-Utilities) were used
# Commands
- ```!add``` - adds account to database, example: ```!add mage1 login password```  
If account already exists (checks profession, in this case mage1) -> the bot will ask if you want to update login/password.  
Can also be used to add permissions for users, example ```!add permissions discord_id``` 
- ```!acc``` - returns all accounts login/password information in PM, example ```!acc```
To retrieve a single account: ```!acc mage1```  
- ```!remove``` - removes specific account (requires profession name), example: ```!remove mage1```  
Can also remove permissions from a user, ```!remove permissions discord_id```  
- ```!list``` - provides list of available accounts by profession name  
- ```!permissions``` - provides a list of users who can use bot functionality  
- ```!help``` - send DM with all commands available and their description (this command is made by default from JDA Utilities, Inherited Command class)
# How to use
In `Main.java` replace String `adminId` with your discord id.   
In file `TOKEN_HOLDER.txt`, insert your bot token.   
Also, H2 server should be set up according to your needs, in my case - the code uses H2 local test server, which can be left unmodified, but ideally, the bot could be hosted somewhere to have unlimited access to accounts information. In this case, of course, access to database is available as long as the project is running.


