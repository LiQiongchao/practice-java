local modname = ...
local _M = {}
_G[modname] = _M


function _M.contains(key, list)
    return list[key] ~= nil
end


function sort_by_asc(x, y)
    return x < y
end


-- t: table
-- f: sort format
-- 根据key的顺序遍历table
function _M.pairs_by_key_order(t)
    local na = {}    -- new array
    for k in pairs(t) do na[#na + 1] = k end
    table.sort(na, sort_by_asc)
    local i = 0

    return function()
        i = i + 1
        return na[i], t[na[i]]
    end
end

return _M
