#!/bin/bash

echo "Waiting for Mongo nodes..."
sleep 15

echo "Init config server RS"
mongosh --host configsvr:27019 <<EOF
try {
  rs.status()
} catch (e) {
  rs.initiate({
    _id: "configRS",
    configsvr: true,
    members: [{ _id: 0, host: "configsvr:27019" }]
  })
}
EOF

echo "Init shard1 RS"
mongosh --host shard1:27018 <<EOF
try {
  rs.status()
} catch (e) {
  rs.initiate({
    _id: "shard1RS",
    members: [{ _id: 0, host: "shard1:27018" }]
  })
}
EOF

echo "Init shard2 RS"
mongosh --host shard2:27020 <<EOF
try {
  rs.status()
} catch (e) {
  rs.initiate({
    _id: "shard2RS",
    members: [{ _id: 0, host: "shard2:27020" }]
  })
}
EOF

echo "Waiting for RS stabilization..."
sleep 10

echo "Add shards to cluster"
mongosh --host mongos:27017 <<EOF
// Add shards to the cluster
sh.addShard("shard1RS/shard1:27018")
sh.addShard("shard2RS/shard2:27020")

// Enable sharding for database
sh.enableSharding("finance")

// Shard the collection
sh.shardCollection(
  "finance.events",
  {currencyId: 1, timestamp: 1}
)

// Assign shards to zones
sh.addShardToZone("shard1RS", "EU")
sh.addShardToZone("shard2RS", "US")


// Define zone ranges
sh.updateZoneKeyRange(
  "finance.events",
  {currencyId: "EUR", timestamp: MinKey},
  {currencyId: "EUR", timestamp: MaxKey},
  "EU"
)

sh.updateZoneKeyRange(
  "finance.events",
  {currencyId: "USD", timestamp: MinKey},
  {currencyId: "USD", timestamp: MaxKey},
  "US"
)

// Set default write concern
use admin
db.runCommand({
  setDefaultRWConcern: 1,
  defaultWriteConcern: { w: "majority" }
})
EOF

echo "Mongo cluster initialized"