package com.gildedrose;

public class TimeSensitiveItemUpdateHelper extends ItemUpdateHelper {
    public static final int URGENT_TIME_SENSITIVE_SELL_DAYS = 10;
    public static final int EARLY_TIME_SENSITIVE_DAYS = 5;

    public TimeSensitiveItemUpdateHelper(Item item) {
        super(item);
    }

    @Override
    public void updateItem() {
        if (isPastSellIn()) {
            minimizeQuality();
        } else {
            increaseQuality();

            if (isInEarlyTimeSensitivePeriod()) {
                increaseQuality();
            }

            if (isInUrgentTimeSensitivePeriod()) {
                increaseQuality();
            }
        }

        decreaseSellIn();
    }

    protected boolean isInUrgentTimeSensitivePeriod() {
        return item.sellIn <= URGENT_TIME_SENSITIVE_SELL_DAYS;
    }

    protected boolean isInEarlyTimeSensitivePeriod() {
        return item.sellIn <= EARLY_TIME_SENSITIVE_DAYS;
    }
}