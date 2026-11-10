#!/bin/bash

# 目标下载目录
DOWNLOAD_DIR="F:/educationProject/健身系统/sportsTraining/server_code/src/main/resources/static/file"

# 唯一URL列表
declare -a URLS=(
    "http://clfile.zggen.cn/20251114/4312cf6ff2594cfa8403d37353987907.css"
    "http://clfile.zggen.cn/20251114/3f7c313b639a4e748ba4449b33d320b5.js"
    "http://clfile.zggen.cn/20251115/5b1faf344fa34c81bc2f4fe542bddc5d.js"
    "http://clfile.zggen.cn/20251115/49fa0e99794f4c2fa9121da64a743807.js"
    "http://clfile.zggen.cn/20251115/38ffcb8fcc554bdc8582b2906e1f95c2.css"
    "http://clfile.zggen.cn/20251115/6e3f314b7e2e4460b9d2be87c8bcb365.js"
    "http://clfile.zggen.cn/20251115/4936d0d3a86141009fe06ef08d3e5297.js"
    "http://clfile.zggen.cn/20251115/ca1e24c3ebbe4117a76d1c175881fa02.js"
    "http://clfile.zggen.cn/20251204/9a6e9f0caaea4abead297db4c1278388.js"
    "http://clfile.zggen.cn/20251114/c7545bbab1504da0b6be2f2d2471c87e.js"
    "http://clfile.zggen.cn/20251114/3f6d667b307e408d93ac93092a0b01e6.js"
    "http://clfile.zggen.cn/20251107/ab4d645a36394460bd4a1dcb03c19174.png"
    "http://clfile.zggen.cn/20250725/889d208e5ddd4107bce6600713cdf6d1.webp"
    "http://clfile.zggen.cn/20250905/e13654edfb0c408e94a59ffc9e43dce0.png"
    "http://clfile.zggen.cn/20251107/ed4301675bd844ba968a859889e0679f.png"
    "http://clfile.zggen.cn/20241205/28785da1592b4ff2b775a168e4b4bb3c.png"
    "http://clfile.zggen.cn/20251110/164e567422a144f99cfe0960021acdf1.png"
    "http://clfile.zggen.cn/20251107/a3f7bd5b156b4029821cff6fc65ec515.png"
    "http://clfile.zggen.cn/20251110/3d4b8600e89543fd97fdefc5525dc3ef.png"
    "http://clfile.zggen.cn/20251107/c3e7e4e7c0d7493fb92a1e3d6249f692.png"
    "http://clfile.zggen.cn/20250728/efa8c4527e0d4b5fa31b9fa123463b16.webp"
    "http://clfile.zggen.cn/20250812/ad45bfcb13364fa7b8da737057b37544.png"
    "http://clfile.zggen.cn/20250820/c58bf847dba74e2692dd45f605836e09.png"
    "http://clfile.zggen.cn/20250820/7732734e8d8a45a3b8d380c38187f23f.png"
    "http://clfile.zggen.cn/20250820/5935e19d1b03400fafb4daa0576783fe.png"
    "http://clfile.zggen.cn/20250820/0c8f3adab0c842558afe60438f413933.png"
    "http://clfile.zggen.cn/20250820/45b59adbdd3d44caac61b8e449e7cd5d.png"
    "http://clfile.zggen.cn/20250820/a802cc2af24d41278511ea6b6e94fefc.png"
    "http://clfile.zggen.cn/20260117/31c1ea7de66648dabe51693a2641da2d.webp"
    "http://clfile.zggen.cn/20250812/8ad090e1a9884096b7b34dc51c7d6534.png"
    "http://clfile.zggen.cn/20250821/b4b59af10b5843129a19ac0abc1d8698.png"
    "http://clfile.zggen.cn/20250821/bb9e7539c86c4dfa8591a65de911938c.png"
    "http://clfile.zggen.cn/20250812/a7227bf80cea46afa563f4737db2d7e3.png"
)

# 需要替换的文件列表
declare -a FILES_TO_UPDATE=(
    "F:/educationProject/健身系统/sportsTraining/client_code/public/index.html"
    "F:/educationProject/健身系统/sportsTraining/client_code/src/assets/css/style.scss"
    "F:/educationProject/健身系统/sportsTraining/client_code/src/main.js"
    "F:/educationProject/健身系统/sportsTraining/client_code/src/views/pages/forget.vue"
    "F:/educationProject/健身系统/sportsTraining/client_code/src/views/pages/forum/detail.vue"
    "F:/educationProject/健身系统/sportsTraining/client_code/src/views/pages/forum/list.vue"
    "F:/educationProject/健身系统/sportsTraining/client_code/src/views/pages/login.vue"
    "F:/educationProject/健身系统/sportsTraining/client_code/src/views/pages/yonghu/center.vue"
    "F:/educationProject/健身系统/sportsTraining/client_code/src/views/pages/yonghu/register.vue"
    "F:/educationProject/健身系统/sportsTraining/manage_code/public/index.html"
    "F:/educationProject/健身系统/sportsTraining/manage_code/src/assets/css/style.scss"
    "F:/educationProject/健身系统/sportsTraining/manage_code/src/main.js"
    "F:/educationProject/健身系统/sportsTraining/manage_code/src/views/forget.vue"
    "F:/educationProject/健身系统/sportsTraining/manage_code/src/views/HomeView.vue"
    "F:/educationProject/健身系统/sportsTraining/manage_code/src/views/login.vue"
)

echo "=== 开始下载静态文件 ==="
echo ""

# 下载所有文件
for url in "${URLS[@]}"; do
    # 提取文件名
    filename=$(basename "$url")
    target_path="$DOWNLOAD_DIR/$filename"

    echo "下载: $url"
    echo "  -> $target_path"

    # 使用curl下载文件
    curl -L -o "$target_path" "$url"

    if [ $? -eq 0 ]; then
        echo "  ✓ 下载成功"
    else
        echo "  ✗ 下载失败"
    fi
    echo ""
done

echo "=== 开始替换URL ==="
echo ""

# 替换所有文件中的URL
for file in "${FILES_TO_UPDATE[@]}"; do
    if [ -f "$file" ]; then
        echo "处理: $file"

        # 使用sed替换URL
        # 将 http://clfile.zggen.cn/YYYYMMDD/filename.ext 替换为 http://localhost:8080/sportsTraining/file/filename.ext
        sed -i 's|http://clfile\.zggen\.cn/[0-9]\{8\}/\([^"'\'')\s]*\)|http://localhost:8080/sportsTraining/file/\1|g' "$file"

        echo "  ✓ 替换完成"
    else
        echo "  ✗ 文件不存在: $file"
    fi
done

echo ""
echo "=== 完成 ==="
echo "已下载 ${#URLS[@]} 个文件到 $DOWNLOAD_DIR"
echo "已更新 ${#FILES_TO_UPDATE[@]} 个文件的URL引用"
