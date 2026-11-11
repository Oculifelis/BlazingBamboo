"""Blazing Bamboo (Unofficial) branding, using the original MIT artwork and palette."""
from pathlib import Path
import sys
from PIL import Image, ImageDraw
sys.path.insert(0,"H:/AI_MC_Modding/_toolkit/logokit")
from bannerkit import Canvas, relief, relief_field, mask_title, save_all
from pixeltitle import pixel_mask
ROOT=Path(__file__).resolve().parents[1]
TEX=ROOT/"common/src/main/resources/assets/blazingbamboo/textures"
INK=(42,14,15,255)
# Crimson shadows and bamboo's amber highlights.
RAMP=[(30,9,12,255),(46,12,15,255),(65,17,17,255),(88,23,19,255),(115,33,18,255),(146,50,20,255),(177,72,25,255),(207,101,31,255),(231,137,44,255),(248,174,67,255),(255,210,105,255)]
FILL=[(255,237,157,255),(255,209,103,255),(247,169,53,255),(224,113,26,255),(177,67,20,255)]
def banner():
 cv=relief(256,80,RAMP,relief_field(seed=1030015,basin=(.52,.5),stretch=(2.2,1.1),freq=.75),glow=(128,36,100),pool=.25)
 mask,w,h=pixel_mask("Blazing Bamboo",2)
 mask_title(cv,mask,(256-w)//2,25,FILL,INK,shadow=RAMP[0])
 mask,w,h=pixel_mask("UNOFFICIAL",1)
 mask_title(cv,mask,(256-w)//2,58,[FILL[1],FILL[2],FILL[3]],INK,shadow=RAMP[0])
 return cv.im

def icon():
 im=Image.new("RGBA",(40,40),INK);d=ImageDraw.Draw(im)
 d.rounded_rectangle((0,0,39,39),radius=5,fill=RAMP[2],outline=RAMP[8],width=2)
 d.rounded_rectangle((3,3,36,36),radius=3,fill=RAMP[0],outline=RAMP[4],width=1)
 for radius,c in [(14,RAMP[1]),(11,RAMP[2]),(8,RAMP[3])]:d.ellipse((20-radius,20-radius,20+radius,20+radius),fill=c)
 spr=Image.open(TEX/"item/blazing_bamboo_item.png").convert("RGBA").resize((32,32),Image.Resampling.NEAREST)
 shadow=Image.new("RGBA",spr.size,INK);shadow.putalpha(spr.getchannel("A"));im.alpha_composite(shadow,(5,6));im.alpha_composite(spr,(4,3))
 d=ImageDraw.Draw(im);d.line((6,1,32,1),fill=FILL[0]);d.line((1,6,1,31),fill=FILL[2])
 return im
if __name__=="__main__":save_all(banner(),icon(),str(ROOT/"branding"))
