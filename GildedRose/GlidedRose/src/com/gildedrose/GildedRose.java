package com.gildedrose;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;

class GildedRose {
    public static final String BACKSTAGE_PASSES_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String BRIE_ITEM_NAME = "Aged Brie";
    public static final String SULFURAS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED_CAKE_ITEM_NAME = "Conjured Mana Cake";
    private Stream<? extends ItemUpdateHelper> itemUpdateHelpers;
    private Map<String, Function<Item, ? extends ItemUpdateHelper>> itemTypes;

    public GildedRose(Item[] items) {
        this.itemUpdateHelpers = Arrays.asList(items).stream().map(this::buildItemUpdateHelper);
        this.itemTypes = new ConcurrentHashMap<>();
        populateItemTypes(itemTypes);
    }

    protected void populateItemTypes(Map<String, Function<Item, ? extends ItemUpdateHelper>> itemTypes) {
        itemTypes.put(BACKSTAGE_PASSES_ITEM_NAME, (item -> { return new TimeSensitiveItemUpdateHelper(item); }));
        itemTypes.put(BRIE_ITEM_NAME, (item -> { return new IncreaseQualityItemUpdateHelper(item); }));
        itemTypes.put(SULFURAS_ITEM_NAME, ( item -> { return new DoNothingItemUpdateHelper(item); }));
        itemTypes.put(CONJURED_CAKE_ITEM_NAME, (item -> {
            return new ConjuredItemUpdateHelper(item);
        }));
    }

    public void updateQuality() {
        this.itemUpdateHelpers.forEach((itemUpdateHelper -> {
            itemUpdateHelper.updateItem();
        }));
    }

    protected ItemUpdateHelper buildItemUpdateHelper(Item item) {
        Function<Item, ? extends ItemUpdateHelper> specificItemUpdateHelper = this.itemTypes.get(item.name);
        if (specificItemUpdateHelper != null) {
            return specificItemUpdateHelper.apply(item);
        }
        return new NormalItemUpdateHelper(item);
    }
}
