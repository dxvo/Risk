import twitter4j.TwitterException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;

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

		return;
	}
}
