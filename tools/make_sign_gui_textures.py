"""Build the sign edit screen textures from the block sign textures, matching vanilla's GUI layout.

gui/signs: the 24x12 board front over the 2x14 post (vanilla 26.2 crops the same rectangles).
gui/hanging_signs: the top bar and board from the hanging sign texture, with vanilla's iron chain links.
"""
import sys
from pathlib import Path
from PIL import Image
ROOT = Path(__file__).resolve().parents[1]
TEX = ROOT / "common/src/main/resources/assets/blazingbamboo/textures"
# Vanilla chain links (identical for every wood), columns x=2..3 and x=12..13, rows y=2..5.
DARK, MID, LIGHT = (37, 44, 61, 255), (62, 68, 83, 255), (73, 80, 101, 255)
CHAIN = [[DARK, LIGHT, LIGHT, MID], [MID, MID, DARK, DARK]]

def sign():
    b = Image.open(TEX / "block/blazing_bamboo_sign.png").convert("RGBA")
    g = Image.new("RGBA", (24, 26))
    g.paste(b.crop((0, 2, 24, 14)), (0, 0))
    g.paste(b.crop((28, 0, 30, 14)), (11, 12))
    return g

def hanging():
    b = Image.open(TEX / "block/blazing_bamboo_hanging_sign.png").convert("RGBA")
    g = Image.new("RGBA", (16, 16))
    g.paste(b.crop((0, 4, 16, 6)), (0, 0))
    g.paste(b.crop((2, 16, 16, 26)), (1, 6))
    for x0 in (2, 12):
        for dx, col in enumerate(CHAIN):
            for dy, px in enumerate(col):
                g.putpixel((x0 + dx, 2 + dy), px)
    return g

if __name__ == "__main__":
    out = Path(sys.argv[1]) if len(sys.argv) > 1 else TEX
    for name, img in (("gui/signs/blazing_bamboo.png", sign()), ("gui/hanging_signs/blazing_bamboo.png", hanging())):
        p = out / name
        p.parent.mkdir(parents=True, exist_ok=True)
        img.save(p)
        print(p)
