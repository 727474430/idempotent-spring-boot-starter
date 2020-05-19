---
--- Created by raindrop.
--- DateTime: 2020/5/18 10:27 下午
---

local token_key = KEYS[1]

local token_value = redis.call("get", token_key)

if token_value == nil then
    return false
end

local result = redis.call("del", token_key)
if result == 0 then
    return false
end

redis.log(redis.LOG_DEBUG, 'remove key: ' .. token_key .. ' success')
return true