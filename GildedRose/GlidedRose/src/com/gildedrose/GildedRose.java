package com.gildedrose;

import java.util.Arrays;
import java.util.List;

class GildedRose {
    public static final int MAXIMUM_QUALITY = 50;
    public static final int MINIMUM_QUALITY = 0;
    public static final int URGENT_TIME_SENSITIVE_SELL_DAYS = 11;
    public static final int EARLY_TIME_SENSITIVE_DAYS = 6;
    public static final int SELL_IN_DEADLINE_DAYS = 0;
    public static final String BACKSTAGE_PASSES_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String BRIE_ITEM_NAME = "Aged Brie";
    public static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED_CAKE_ITEM_NAME = "Conjured Mana Cake";
    List<Item> items;

    public GildedRose(Item[] items) {
        this.items = Arrays.asList(items);
    }

    public void updateQuality() {
        this.items.stream().forEach((item -> { updateItem(item); }));
    }

    protected void updateItem(Item item) {
        if (!hasPermanentQuality(item)) {
            if (hasTimeSensitiveSellIn(item)) {
                updateTimeSensitiveSellIn(item);
            } else if (hasIncreasingQuality(item)) {
                updateIncreasingQuality(item);
            } else if (isConjuredItem(item)) {
                updateConjuredItem(item);
            } else {
                updateNormalItem(item);
            }
        }
    }

    protected void updateTimeSensitiveSellIn(Item item) {
        if (isPastSellIn(item)) {
            minimizeQuality(item);
        } else {
            increaseQuality(item);

            if (isInEarlyTimeSensitivePeriod(item)) {
                increaseQuality(item);
            }

            if (isInUrgentTimeSensitivePeriod(item)) {
                increaseQuality(item);
            }
        }

        decreaseSellIn(item);
    }

    protected void updateIncreasingQuality(Item item) {
        increaseQuality(item);
        decreaseSellIn(item);
    }


    protected void updateConjuredItem(Item item) {
        decreaseQuality(item);
        decreaseQuality(item);
        decreaseSellIn(item);
    }

    protected void updateNormalItem(Item item) {
        decreaseQuality(item);
        if (isPastSellIn(item)) {
            // past sell in, double up the decreasing
            decreaseQuality(item);
        }
        decreaseSellIn(item);
    }

    protected void minimizeQuality(Item item) {
        item.quality = MINIMUM_QUALITY;
    }

    protected boolean isInUrgentTimeSensitivePeriod(Item item) {
        return item.sellIn < URGENT_TIME_SENSITIVE_SELL_DAYS;
    }

    protected boolean isInEarlyTimeSensitivePeriod(Item item) {
        return item.sellIn < EARLY_TIME_SENSITIVE_DAYS;
    }

    protected boolean isPastSellIn(Item item) {
        return item.sellIn < SELL_IN_DEADLINE_DAYS;
    }

    protected void decreaseQuality(Item item) {
        if (item.quality > MINIMUM_QUALITY) {
            item.quality -= 1;
        }
    }

    protected void increaseQuality(Item item) {
        if (item.quality < MAXIMUM_QUALITY) {
            item.quality += 1;
        }
    }

    protected void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }

    protected boolean hasTimeSensitiveSellIn(Item item) {
        return item.name.equals(BACKSTAGE_PASSES_ITEM_NAME);
    }

    protected boolean hasIncreasingQuality(Item item) {
        return item.name.equals(BRIE_ITEM_NAME);
    }

    protected boolean hasPermanentQuality(Item item) {
        return item.name.equals(SULFURAS_ITEM_NAME);
    }

    protected boolean isConjuredItem(Item item) {
        return item.name.equals(CONJURED_CAKE_ITEM_NAME);
    }
}
