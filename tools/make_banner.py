"""Blazing Bamboo (Unofficial) branding, using the original MIT artwork and palette.

Banner (round 2, "make more thematic"): a crimson forest at night in the Nether. Golden bamboo groves frame the
title on both sides, near stalks bright, far ones dark crimson, with flame leaves at the nodes, embers rising, a lava
glow behind the title and crimson nylium underfoot. Stalk and leaf colours come from the mod's own textures.
Icon: approved in round 1; only its corners changed (clear outside the rounded frame).

    python tools/make_banner.py   -> branding/banner_*.png, branding/icon_*.png
"""
from pathlib import Path
import math
import random
import sys
from PIL import Image, ImageDraw
sys.path.insert(0, "H:/AI_MC_Modding/_toolkit/logokit")
from bannerkit import Canvas, mask_title, save_all, ramp, dither, HEX
from pixeltitle import pixel_mask

ROOT = Path(__file__).resolve().parents[1]
TEX = ROOT / "common/src/main/resources/assets/blazingbamboo/textures"
INK = (42, 14, 15, 255)
# Crimson shadows and bamboo's amber highlights.
RAMP = [(30, 9, 12, 255), (46, 12, 15, 255), (65, 17, 17, 255), (88, 23, 19, 255), (115, 33, 18, 255),
        (146, 50, 20, 255), (177, 72, 25, 255), (207, 101, 31, 255), (231, 137, 44, 255), (248, 174, 67, 255),
        (255, 210, 105, 255)]
FILL = [(255, 237, 157, 255), (255, 209, 103, 255), (247, 169, 53, 255), (224, 113, 26, 255), (177, 67, 20, 255)]
W, H = 256, 80
GROUND = 73
NYLIUM = [HEX("#2a0a0e"), HEX("#5c1018"), HEX("#7b1a20"), HEX("#94262a")]


# Picked from blazing_bamboo_stalk.png / blazing_bamboo_large_leaves.png, darkest first.
STALK = [HEX("#6e2c0c"), HEX("#b15b10"), HEX("#f2c436"), HEX("#f5e317"), HEX("#f7f37e")]
LEAVES = [HEX("#a14f11"), HEX("#ca6a12"), HEX("#efb815"), HEX("#fed20b"), HEX("#fbec3e")]


def mix(a, b, f):
    return tuple(int(a[i] * (1 - f) + b[i] * f) for i in range(3)) + (255,)


def backdrop(cv):
    """Night over the crimson forest: near-black crimson at the top, warming to a lava glow behind the title."""
    sky = RAMP[:7]
    for y in range(H):
        for x in range(W):
            t = 0.06 + 0.40 * (y / H) ** 1.6
            g = max(0.0, 1 - math.hypot((x - 128) / 112, (y - 46) / 36))
            cv.put(x, y, ramp(sky, t + g ** 1.5 * 0.6, x, y))


def fungus(cv, cx, top, cap, col, rim):
    """A far huge crimson fungus in flat silhouette: a domed cap on a thin stem down to the ground."""
    for y in range(top, GROUND + 1):
        for x in range(cx - 1, cx + 2):
            cv.put(x, y, col)
    rows = cap // 3
    for r in range(rows + 1):
        half = cap / 2 * math.sqrt(max(0.0, 1 - ((rows - r) / (rows + 0.5)) ** 2))
        y = top - rows + r
        for x in range(int(cx - half), int(cx + half) + 1):
            cv.put(x, y, rim if r == 0 or x in (int(cx - half), int(cx + half)) and r < 2 else col)
    for x in range(cx - cap // 2 + 1, cx + cap // 2, 4):                    # hanging weeping vines under the cap
        for y in range(top + 1, top + 2 + (x * 7) % 5):
            cv.put(x, y, col)


def stalk(cv, x, w, top, cols, nodes, lit_right, seed, leaves=None, ink=None):
    """One bamboo stalk from top to the ground. Columns run from a dark edge to a pale highlight on the side facing
    the title's glow; a dark node ring every few pixels with a bright swelling under it; flame leaves at some nodes."""
    rnd = random.Random(seed)
    n = len(cols)
    shade = [0, 1, 2, 3, 4] if w >= 5 else [0, 2, 3] if w == 3 else [1, 3]
    shade = [min(n - 1, i * (n - 1) // 4) for i in shade]
    if not lit_right:
        shade = shade[::-1]
    for y in range(max(0, top), GROUND + 1):
        p = (y - top) % nodes
        for i in range(w):
            c = cols[shade[i]]
            if p == 0:
                c = cols[max(0, shade[i] - 2)] if shade[i] > 0 else cols[0]      # the node's dark ring
            elif p == 1:
                c = cols[min(n - 1, shade[i] + 1)]                                # the swelling under it catches light
            elif p == 2 and dither(x + i, y, 0.5):
                c = cols[min(n - 1, shade[i] + 1)]
            cv.put(x + i, y, c)
        if ink:
            cv.put(x - 1, y, ink)
            cv.put(x + w, y, ink)
    if ink and top > 0:
        for i in range(-1, w + 1):
            cv.put(x + i, top - 1, ink)
    if not leaves:
        return
    side = 1 if lit_right else -1
    for y in range(top + nodes, GROUND - 8, nodes):
        if rnd.random() < 0.6:
            side = -side if rnd.random() < 0.6 else side
            leaf(cv, x + w + 1 if side > 0 else x - 2, y, side, leaves, ink)


LEAF = ["......45",
        "....443.",
        "..4432..",
        ".3321...",
        "2210....",
        "10......"]                                              # a flame-tongue leaf rising out of the node; digits pick the colour


def leaf(cv, x, y, side, cols, ink):
    rows = len(LEAF)
    for r, row in enumerate(LEAF):
        for c, ch in enumerate(row):
            if ch == ".":
                continue
            px, py = x + (c if side > 0 else -c), y - rows + r + 1
            cv.put(px, py, cols[min(len(cols) - 1, int(ch))])
            below = LEAF[r + 1][c] if r + 1 < rows else "."
            if ink and below == ".":
                cv.put(px, py + 1, ink)                          # ink under each leaf so it reads off what's behind


def embers(cv, n, seed):
    rnd = random.Random(seed)
    cols = [FILL[0], FILL[1], FILL[1], FILL[2]]
    placed = 0
    while placed < n:
        x, y = rnd.randrange(3, W - 3), rnd.randrange(3, GROUND - 3)
        if 34 < x < 222 and 18 < y < 72:
            continue                                             # nothing on or round the title
        placed += 1
        cv.put(x, y, rnd.choice(cols))
        if rnd.random() < 0.45:
            cv.put(x, y + 1, RAMP[7])                            # a short glowing trail under a rising ember
            if rnd.random() < 0.5:
                cv.put(x + rnd.choice((-1, 1)), y + 2, RAMP[5])


def ground(cv):
    """Crimson nylium underfoot: an ink edge, a lit top row, a dark warty body with a few brighter specks."""
    rnd = random.Random(7)
    for x in range(W):
        top = GROUND + (1 if math.sin(x / 6.0) + math.sin(x / 2.7 + 1) > 1.1 else 0)
        cv.put(x, top - 1, INK)
        for y in range(top, H):
            d = y - top
            c = NYLIUM[3] if d == 0 else NYLIUM[2] if d == 1 or (d == 2 and dither(x, y, 0.5)) else NYLIUM[1] if d < 4 else NYLIUM[0]
            if d >= 2 and rnd.random() < 0.07:
                c = NYLIUM[2]
            cv.put(x, y, c)
    for x in range(2, W - 2, 1):                                  # crimson roots: small red tufts on the nylium
        if rnd.random() < 0.12:
            hgt = rnd.randint(1, 3)
            for k in range(hgt):
                cv.put(x + (k % 2 if x % 3 else 0), GROUND - 1 - k, NYLIUM[3] if k == hgt - 1 else NYLIUM[2])
            cv.put(x, GROUND - 1 - hgt, INK)


def banner():
    cv = Canvas(W, H)
    backdrop(cv)
    stalk_c, leaf_c = STALK, LEAVES
    # far layer: huge crimson fungi and thin dark stalks, flat silhouettes in the haze
    for cx, top, cap in ((24, 26, 30), (232, 22, 34), (70, 50, 22), (106, 60, 16), (150, 58, 18), (188, 48, 24)):
        fungus(cv, cx, top, cap, RAMP[1], RAMP[4])
    rnd = random.Random(11)
    for x in list(range(3, 62, 6)) + list(range(196, 254, 6)):
        stalk(cv, x + rnd.randint(-1, 1), 2, rnd.randint(-4, 16), [RAMP[2], RAMP[3], RAMP[4]], 9, x < 128, x)
    # mid layer: dimmed gold stalks, a step back into the glow
    mid = [mix(c, RAMP[3], 0.5) for c in stalk_c]
    mid_leaf = [mix(c, RAMP[3], 0.5) for c in leaf_c]
    for x, top in ((26, 4), (33, 22), (44, 36), (212, 34), (219, 14), (226, 2)):
        stalk(cv, x, 3, top, mid, 11, x < 128, x * 3, leaves=mid_leaf, ink=RAMP[1])
    # front layer: the bright grove, thick stalks lit on the side facing the title's glow
    for x, top in ((5, -2), (17, 6), (37, -1), (214, 0), (233, 8), (247, -3)):
        stalk(cv, x, 5, top, stalk_c, 14, x < 128, x * 7, leaves=leaf_c, ink=INK)
    ground(cv)
    embers(cv, 46, 3)
    mask, w, h = pixel_mask("Blazing Bamboo", 2)
    mask_title(cv, mask, (W - w) // 2, 25, FILL, INK, shadow=RAMP[0])
    mask, w, h = pixel_mask("UNOFFICIAL", 1)
    mask_title(cv, mask, (W - w) // 2, 58, [FILL[1], FILL[2], FILL[3]], INK, shadow=RAMP[0])
    return cv.im


def icon():
    im = Image.new("RGBA", (40, 40), INK)
    d = ImageDraw.Draw(im)
    d.rounded_rectangle((0, 0, 39, 39), radius=5, fill=RAMP[2], outline=RAMP[8], width=2)
    d.rounded_rectangle((3, 3, 36, 36), radius=3, fill=RAMP[0], outline=RAMP[4], width=1)
    for radius, c in [(14, RAMP[1]), (11, RAMP[2]), (8, RAMP[3])]:
        d.ellipse((20 - radius, 20 - radius, 20 + radius, 20 + radius), fill=c)
    spr = Image.open(TEX / "item/blazing_bamboo_item.png").convert("RGBA").resize((32, 32), Image.Resampling.NEAREST)
    shadow = Image.new("RGBA", spr.size, INK)
    shadow.putalpha(spr.getchannel("A"))
    im.alpha_composite(shadow, (5, 6))
    im.alpha_composite(spr, (4, 3))
    d = ImageDraw.Draw(im)
    d.line((6, 1, 32, 1), fill=FILL[0])
    d.line((1, 6, 1, 31), fill=FILL[2])
    # round 1 asked "corners?": everything outside the frame's rounded edge is now clear instead of dark ink
    edge = Image.new("L", (40, 40), 0)
    ImageDraw.Draw(edge).rounded_rectangle((0, 0, 39, 39), radius=5, fill=255)
    im.putalpha(edge)
    return im


if __name__ == "__main__":
    save_all(banner(), icon(), str(ROOT / "branding"))
