package com.dark.rs2.content;

import java.util.Calendar;
//Add your Client Import Here

import com.dark.rs2.entity.item.Item;
import com.dark.rs2.entity.player.Player;
import com.dark.rs2.entity.player.net.out.impl.SendMessage;

public class DailyLogin {
	
	static Calendar cal = Calendar.getInstance();
    static int year = cal.get(Calendar.YEAR);
    static int date = cal.get(Calendar.DAY_OF_MONTH);
    static int month = cal.get(Calendar.MONTH)+1;
    
    private Player p;
    
	public DailyLogin(Player player) {
		this.p = player;
	}
	public static int getLastDayOfMonth(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month -1, date);
		int maxDay = calendar.getActualMaximum(date);
        return maxDay;
	}
	public void IncreaseStreak() {
		Player.LastLoginYear = year;
		Player.LastLoginDate = date;
		Player.LastLoginMonth = month;
		Player.LoginStreak++;
		switch(Player.LoginStreak) {
		case 1: //1st day
			p.getInventory().add(new Item(1957, 1));
			p.send(new SendMessage("You have logged in for the first time! log in tomorrow for a better reward"));
			break;
		case 2: //2 logins in a row
			//Add reward for 2 logins here
			p.send(new SendMessage("You have logged in for the second time! log in tomorrow for a better reward"));
			break;
		case 3: //3 logins
			//Add reward for 3 logins here
			p.send(new SendMessage("You have logged in for the third time! log in tomorrow for a better reward"));
			break;
		}
	}
	public void RefreshDates() {
		cal = Calendar.getInstance();
	   year = cal.get(Calendar.YEAR);
	   date = cal.get(Calendar.DAY_OF_MONTH);
	   month = cal.get(Calendar.MONTH)+1;
	}
	public void LoggedIn() {
		RefreshDates();
		if ((Player.LastLoginYear == year) && (Player.LastLoginMonth == month) && (Player.LastLoginDate == date)) {
			p.send(new SendMessage("You have already recieved your daily reward for today."));
			return;
		}
		if ((Player.LastLoginYear == year) && (Player.LastLoginMonth == month) && (Player.LastLoginDate == (date - 1)))
			IncreaseStreak();
		else if ((Player.LastLoginYear == year) && ((Player.LastLoginMonth + 1) == month) && (1 == date))
			IncreaseStreak();
		else if ((Player.LastLoginYear == year-1) && (1 == month) && (1 == date))
			IncreaseStreak();
		else {
			Player.LoginStreak = 0;
			IncreaseStreak();
		}
	}
}