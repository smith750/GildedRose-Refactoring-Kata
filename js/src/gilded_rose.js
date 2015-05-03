function Item(name, sell_in, quality) {
  this.name = name;
  this.sell_in = sell_in;
  this.quality = quality;
}

var items = []

var MAX_QUALITY = 50
var MIN_QUALITY = 0
var TIME_SENSITIVE_DAYS_FASTER = 10
var TIME_SENSITIVE_DAYS_FASTEST = 5
var SELL_IN_DATE = 0

function update_quality_for_item(item) {
	if (item.name != 'Aged Brie' && item.name != 'Backstage passes to a TAFKAL80ETC concert' && item.name != 'Sulfuras, Hand of Ragnaros') {
    	decrease_quality(item)
    } else {
        increase_quality(item)
        if (item.name == 'Backstage passes to a TAFKAL80ETC concert') {
          if (item.sell_in <= TIME_SENSITIVE_DAYS_FASTER) {
            increase_quality(item)
          }
          if (item.sell_in <= TIME_SENSITIVE_DAYS_FASTEST) {
            increase_quality(item)
          }
        }
    }
    if (item.name != 'Sulfuras, Hand of Ragnaros') {
      decrease_sell_in(item)
    }
    if (item.sell_in < SELL_IN_DATE) {
      if (item.name != 'Aged Brie') {
        if (item.name != 'Backstage passes to a TAFKAL80ETC concert') {
            if (item.name != 'Sulfuras, Hand of Ragnaros') {
              decrease_quality(item)
            }
        } else {
          clear_quality(item)
        }
      } else {
        increase_quality(item)
      }
    }
}

function increase_quality(item) {
	if (item.quality < MAX_QUALITY) {
		item.quality = item.quality + 1
	}
}

function decrease_quality(item) {
	if (item.quality > MIN_QUALITY) {
		item.quality = item.quality - 1
	}
}

function clear_quality(item) {
	item.quality = MIN_QUALITY
}

function decrease_sell_in(item) {
	item.sell_in = item.sell_in - 1
}

function update_quality() {
    items.map(update_quality_for_item)
}

module.exports.Item = Item;
module.exports.items = items;
module.exports.update_quality = update_quality;