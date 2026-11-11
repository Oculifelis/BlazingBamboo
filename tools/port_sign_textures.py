"""Repack the original MIT sign textures for Minecraft's block sign UV layout."""
from pathlib import Path
from PIL import Image
ROOT=Path(__file__).resolve().parents[1]
TEX=ROOT/"common/src/main/resources/assets/blazingbamboo/textures"
def repack(source,target,rectangles):
 old=Image.open(TEX/source).convert("RGBA")
 assert old.size==(64,32)
 new=Image.new("RGBA",(32,32))
 for src,dest in rectangles:
  tile=old.crop(src)
  assert tile.size==(dest[2]-dest[0],dest[3]-dest[1])
  new.paste(tile,dest[:2])
 new.save(TEX/target)
 # Every output pixel comes from the original texture or the transparent background.
 assert set(new.getdata()) <= set(old.getdata()) | {(0,0,0,0)}
repack("entity/signs/blazing_bamboo.png","block/blazing_bamboo_sign.png",[
 ((28,2,52,14),(0,16,24,28)),((2,2,26,14),(0,2,24,14)),
 ((26,2,28,14),(24,2,26,14)),((0,2,2,14),(24,16,26,28)),
 ((2,0,26,2),(0,0,24,2)),((26,0,50,2),(0,28,24,30)),
 ((6,16,8,30),(28,16,30,30)),((2,16,4,30),(28,0,30,14)),
 ((4,16,6,30),(30,0,32,14)),((0,16,2,30),(30,16,32,30)),
 ((4,14,6,16),(28,30,30,32))])
repack("entity/signs/hanging/blazing_bamboo.png","block/blazing_bamboo_hanging_sign.png",[
 ((18,14,32,24),(18,16,32,26)),((2,14,16,24),(2,16,16,26)),
 ((16,14,18,24),(16,16,18,26)),((0,14,2,24),(0,16,2,26)),
 ((2,12,16,14),(2,14,16,16)),((16,12,30,14),(2,26,16,28)),
 ((24,4,40,6),(0,7,16,9)),((4,4,20,6),(0,4,16,6)),
 ((20,4,24,6),(16,4,20,6)),((0,4,4,6),(16,7,20,9)),
 ((4,0,20,4),(0,0,16,4)),((20,0,36,4),(0,9,16,13)),
 ((14,6,26,12),(20,0,32,6)),((0,6,3,12),(22,7,25,13)),
 ((6,7,9,11),(28,8,31,12))])
