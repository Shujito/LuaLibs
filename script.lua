function printf(...)
	print(string.format(...))
end

local function touhous()
	local reimu = Person:new():name('Reimu'):lastname('Hakurei'):age(19)
	local sanae = Person:new():name('Sanae'):lastname('Kochiya'):age(17)	
	print(reimu:greet())
	print(sanae:greet())
	print(reimu:meet(sanae))
	print(sanae:meet(reimu))
	print(sanae:meet(sanae))
	print(reimu:meet(reimu))
end

local function files()
	local file = File:open('pangrams.txt')
	local contents = {}
	print('contents:')
	while true do
		local line = file:readline()
		if not line then break end
		table.insert(contents,line)
	end
	print(#contents)
	file:close()
end

local function filechars()
	local file = File:open('pangrams.txt')
	local chars = {}
	while true do
		local char = file:read()
		if char < 0 then break end
		table.insert(chars,string.format('%02X',char))
	end
	print('local chars = {'..table.concat(chars,',')..'}')
end

local function filetouhous()
	local file = File:open('touhous.txt')
	local touhous = {}
	for i=1,3 do
		local th = Person:new()
			:name(file:readline())
			:lastname(file:readline())
			:age(file:readline())
		print(th:greet())
		th.file = file
		table.insert(touhous,th)
	end
	file:close()
	for i=1,#touhous do
		for j=1,#touhous do
			print(touhous[i]:meet(touhous[j]))
		end
	end
end

return filetouhous