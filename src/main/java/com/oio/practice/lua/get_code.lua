local redis = require("resty.redis")
local cjson = require ("cjson")
local comm = require("comm")
local cjsonSafe = require("cjson.safe")
local request_method = ngx.var.request_method
local args = nil
local otherms = nil
local acms = nil
local prefix = nil
local areacode = nil
local post_data =nil
local post_json = nil
local res,err = nil
local appkey = nil

if "GET" == request_method then
    args = ngx.req.get_uri_args()
elseif "POST" == request_method then
    ngx.req.read_body()
    appkey = ngx.req.get_headers()["appkey"]
    post_data = ngx.req.get_body_data()
    post_json = cjsonSafe.decode(post_data)
    args =cjson.encode(post_json)
end
for key, val in comm.pairs_by_key_order(post_json) do
         if key == "otherms" then
            otherms = val
         end
         if post_json["otherms"] == nil then
            otherms = nil
            if key == "acms" then
               acms = val
            end
         end
end
     local cache = redis.new()
     local ok, err = cache.connect(cache, '101.200.221.216', '6381')
     cache:auth("acsystem")
if otherms ~= nil then
     areacode = string.sub(otherms,1,3)
     prefix = string.sub(otherms,1,7)
     res,err = cache:hget("NUMPREFIX:"..prefix..":GZAXB","areacode")
   else
      prefix = string.sub(acms,1,7)
      res,err = cache:hget("NUMPREFIX:"..prefix..":GZAXB","areacode")
   end
local post_data = cjson.encode(post_json)
 if (res == "029") or (areacode == "029") then
       local res = ngx.location.capture('/xian_proxy', {method=ngx.HTTP_POST,body=post_data})
       ngx.header.content_type = "application/json"
       ngx.say(res.body)
    else
       local res = ngx.location.capture('/v1_proxy', {method=ngx.HTTP_POST,
                                         body=post_data})
       ngx.header.content_type = "application/json"
       ngx.say(res.body)
    end
 if not res then
            ngx.say("failed to get hello: ", err)
            return
    end
  local ok, err = cache:close()
 if not ok then
        ngx.say("failed to close:", err)
        return
    end
