val g = spark.dseGraph("s")
g.find("(account)-[owns]->(fund); (fund)-[holds]->(holding); (holding)-[has]->(sec)").filter(" account.`~label` = 'account' and owns.`~label` = 'owns' and holds.`~label` = 'holds' and has.`~label` = 'asset' and sec.`~label` = 'security'") .select($"sec.symbol", $"account.name",  $"holding.quantity").registerTempTable("accounts")

spark.sql("select * from accounts").show

spark.sql("select name, sum(value*quantity) as val from  tick_data.tick_data t join accounts a on t.symbol = a.symbol where date = '2017-08-30 15:59:00.0' group by name").show

spark.sql ("select name, quantity, value as val, a.symbol from  tick_data.tick_data t join accounts a on t.symbol = a.symbol where date = '2017-08-30 15:59:00.0'").show


