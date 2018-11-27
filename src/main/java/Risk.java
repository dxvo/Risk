import twitter4j.TwitterException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;

/***
 * The Risk class is main class that starts the application
 * The telegramBotsApi object gives the options to start the game from chat Bot
 * @author 
 * @version 1.0
 * @since 2018-10-01
 */
public class Risk
{
	public static void main(String[] args)
	{


		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new chatbot());//register the chatbot

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		//game now starts in chatbox
		 //GameMaster gameMaster = new GameMaster();
		 //gameMaster.gameStart();

		return;
	}
}
