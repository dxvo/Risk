import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class chatbot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "dwc_risk_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "730204765:AAHqIUzXasdRJsZUDySybOi_yX4BBZOtT58";
    }
}
