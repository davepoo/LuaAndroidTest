local timeSinceSpawn = 0
local SPAWN_INTERVAL = 1.0

-- spawn test entities every x seconds
function OnUpdateSceneBegin()
  timeSinceSpawn = timeSinceSpawn + Timer:GetTimeDiff()
  
  if timeSinceSpawn > SPAWN_INTERVAL then
  
    local numObjects = 0
  
    --spawn a new entity(s)
    for i=1,3 do
      local objects = Game:InstantiatePrefab(Vision.hkvVec3(i*40,0,100), "Prefabs/testentityprefab.vprefab")
      if objects ~= nil then
        numObjects = numObjects + 1
      end
    end    
          
    Debug:PrintLine("Instantiated " ..  tostring(numObjects) .. " objects via prefab!")          
    
    --reset timer
    timeSinceSpawn = 0
  end
  
  
end
