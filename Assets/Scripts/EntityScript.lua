-- Test spawn entity
local MAX_AGE = 1.8

function OnThink(self)
  -- initialise
  if self.age == nil then
	  self.age = 0
    
    -- give a little upwards boost        
    local rb = self:GetComponentOfType("vHavokRigidBody")
    if rb ~= nil then
      rb:ApplyLinearImpulse( Vision.hkvVec3( 0, 0, 1000 ) )
    end    
  end
  
  -- remove if too old
  if self.age > MAX_AGE then
    self:Remove()
  end
  
  -- update timer
  self.age = self.age + Timer:GetTimeDiff()
  
end
