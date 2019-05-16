

-- print("hello")

--[[
多行注释
--
 ]]

-- print(type(nil)) -- nil

--[[
tab1 = { key1 = "val1", key2 = "val2", "val3" }
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end

table = {key1="aa", key2="bb"}
print(table.key2)
--]]

-- 循环
args = {"aa", "bb", "cc"}
for k, v in pairs(args) do
    if "bb" == v then
        print("true")
    else
        print("false")
    end
end



