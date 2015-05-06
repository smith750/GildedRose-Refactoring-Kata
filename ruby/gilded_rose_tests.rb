require File.join(File.dirname(__FILE__), 'gilded_rose')
require 'test/unit'

class TestUntitled < Test::Unit::TestCase

  def test_regular_item1
    assert_item(Item.new(name="+5 Dexterity Vest", sell_in=10, quality=20), 19, 9)
  end
  
  def test_increasing_quality_item
    assert_item(Item.new(name="Aged Brie", sell_in=2, quality=0), 1, 1)
  end
  
  def test_regular_item2
    assert_item(Item.new(name="Elixir of the Mongoose", sell_in=5, quality=7), 6, 4)
  end
  
  def test_unchanging_item1
    assert_item(Item.new(name="Sulfuras, Hand of Ragnaros", sell_in=0, quality=80), 80, 0)
  end
  
  def test_unchanging_item2
    assert_item(Item.new(name="Sulfuras, Hand of Ragnaros", sell_in=-1, quality=80), 80, -1)
  end
  
  def test_time_sensitive_item1
    assert_item(Item.new(name="Backstage passes to a TAFKAL80ETC concert", sell_in=15, quality=20), 21, 14)
  end
  
  def test_time_sensitive_item2
    assert_item(Item.new(name="Backstage passes to a TAFKAL80ETC concert", sell_in=10, quality=49), 50, 9)
  end
  
  def test_time_sensitive_item3
    assert_item(Item.new(name="Backstage passes to a TAFKAL80ETC concert", sell_in=5, quality=49), 50, 4)
  end
  
  def test_conjured_item
    assert_item(Item.new(name="Conjured Mana Cake", sell_in=3, quality=6), 4, 2)
  end

  def assert_item(item, expected_quality, expected_sell_in)
	items = [item]
    GildedRose.new(items).update_quality()
    assert_equal items[0].quality, expected_quality
	assert_equal items[0].sell_in, expected_sell_in
  end
end