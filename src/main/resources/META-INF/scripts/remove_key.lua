---
--- Created by raindrop.
--- DateTime: 2020/5/18 10:27 下午
---

local token_key = KEYS[1]
local token_value = ARGV[1]
if redis.call('get', token_key) == token_value then
    redis.log(redis.LOG_DEBUG, 'remove key: ' .. token_key .. ' success')
    return redis.call("del", token_key)
else
    return 0
end
