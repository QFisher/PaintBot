package bot;

import sx.blah.discord.api.*;
import sx.blah.discord.api.events.*;
import sx.blah.discord.handle.impl.events.*;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.*;

public class InterfaceListener implements IListener<MessageReceivedEvent> {
	
	IUser gameHost;
	
	@Override
	public void handle(MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		IChannel channel = event.getChannel();
		IDiscordClient client = event.getClient();
		try {
			new MessageBuilder(client).withChannel(channel).withContent(message.getContent()).build();
		}
		catch (RateLimitException e) { // RateLimitException thrown. The bot is sending messages too quickly!
			System.err.print("Sending messages too quickly!");
			e.printStackTrace();
		} 
		catch (DiscordException e) { // DiscordException thrown. Many possibilities. Use getErrorMessage() to see what went wrong.
			System.err.print(e.getErrorMessage()); // Print the error message sent by Discord
			e.printStackTrace();
		} 
		catch (MissingPermissionsException e) { // MissingPermissionsException thrown. The bot doesn't have permission to send the message!
			System.err.print("Missing permissions for channel!");
			e.printStackTrace();
		}
	}

}
