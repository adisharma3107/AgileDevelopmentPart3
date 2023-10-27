package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAXIMUM_QUALITY = 50;
    public static final int BACKSTAGE_PASS_THRESHOLD1 = 11;
    public static final int BACKSTAGE_PASS_THRESHOLD2 = 6;
    public static final String CONJURED = "Conjured";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            handleIfNormalItem(item);
            handleIfAgedBrie(item);
            handleBackstagePasses(item);
            handleIfSulfuras(item);
            handleConjured(item);
        }
    }

    private void handleConjured(Item item) {
        if (isConjured(item)) {
            item.sellIn--;
            item.quality = item.quality - 2;
        }
    }

    private boolean isConjured(Item item) {
        return item.name.equals(CONJURED);
    }

    private void handleIfSulfuras(Item item) {
        // We always write the least amount of code to make the pin-down
        // tests go green. In this case, we didn't have to write any
        // code -- so this is the correct solution.
    }

    private void handleBackstagePasses(Item item) {
        if (isBackstagePasses(item)) {
            item.sellIn--;
            if (item.sellIn <= 0) {
                item.quality = 0;
            } else if (item.sellIn < BACKSTAGE_PASS_THRESHOLD2) {
                item.quality = item.quality + 3;
            } else if (item.sellIn < BACKSTAGE_PASS_THRESHOLD1) {
                item.quality = item.quality + 2;
            } else {
                item.quality++;
            }
            if (item.quality > MAXIMUM_QUALITY) {
                item.quality = MAXIMUM_QUALITY;
            }
        }
    }

    private void handleIfAgedBrie(Item item) {
        if (isAgedBrie(item)) {
            item.sellIn--;
            if (item.sellIn <= 0) {
                item.quality = item.quality + 2;
            } else {
                item.quality++;
            }
            if (item.quality > MAXIMUM_QUALITY) {
                item.quality = MAXIMUM_QUALITY;
            }
        }
    }

    private void handleIfNormalItem(Item item) {
        if (isNormalItem(item)) {
            item.sellIn--;
            if (item.sellIn <= 0) {
                item.quality = item.quality - 2;
            } else {
                item.quality--;
            }
            if (item.quality < 0) {
                item.quality = 0;
            }
        }
    }

    private boolean isNormalItem(Item item) {
        return !(isBackstagePasses(item) || isSulfuras(item) || isAgedBrie(item) || isConjured(item));
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }
}