class ItemUpdater
	MAX_QUALITY = 50
	MIN_QUALITY = 0
	TIME_SENSITIVE_DAYS_FASTER = 10
	TIME_SENSITIVE_DAYS_FASTEST = 5
	SELL_IN_DATE = 0
	
	def initialize(item)
		@item = item
		@updater = updater
	end
		
	def increase_quality
		if @item.quality < MAX_QUALITY
			@item.quality += 1
		end
	end
		
	def decrease_quality
		if @item.quality > MIN_QUALITY
			@item.quality -= 1
		end
	end
	
	def clear_quality
		@item.quality = 0
	end
		
	def decrease_sell_in
		@item.sell_in -= 1
	end
	
	def update_quality()
		puts @item.name
		if @item.name == "Aged Brie"
			increase_quality
			decrease_sell_in
		elsif @item.name == "Backstage passes to a TAFKAL80ETC concert"
			if @item.sell_in < SELL_IN_DATE
				clear_quality
			else
				increase_quality
				if @item.sell_in <= TIME_SENSITIVE_DAYS_FASTER
					increase_quality
				end
				if @item.sell_in <= TIME_SENSITIVE_DAYS_FASTEST
					increase_quality
				end
			end
			decrease_sell_in
		elsif @item.name == "Sulfuras, Hand of Ragnaros"
			# do nothing
		else
			decrease_quality
			decrease_sell_in
		end
	end
end

class GildedRose
  def initialize(items)
    @items = items
	@updaters = []
	@items.each do |item|
		@updaters << ItemUpdater.new(item)
	end
  end

  def update_quality()
    @updaters.each do |updater|
		updater.update_quality
	end
  end
end

class Item
  attr_accessor :name, :sell_in, :quality

  def initialize(name, sell_in, quality)
    @name = name
    @sell_in = sell_in
    @quality = quality
  end

  def to_s()
    "#{@name}, #{@sell_in}, #{@quality}"
  end
end