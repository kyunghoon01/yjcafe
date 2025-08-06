# ☕ YJCafe

YJCafeは、学内カフェが混雑する時間帯に、学生や職員の利便性を高めるために開発された予約管理システムです。  
予約機能に加えて、学校と連携したイベント管理やコミュニティ機能もサポートし、カフェ利用の快適さを向上させます。

---

## 📦 プロジェクト構成と技術スタック
<pre>
src/
├── yjcafe/                        # メインパッケージ
│   ├── Main.java                 # アプリケーション起動クラス
│   ├── TopPanel.java            # GUI共通上部パネル
│   ├── YJCafe.java              # メインフレーム
│   ├── Loading.java             # ローディング画面処理
│   ├── Page.java                # 画面遷移制御クラス
│   └── page/                    # 各種画面とロジックを管理
│       ├── CoffeeInfo.java         # コーヒー情報表示
│       ├── DateReservation.java    # 日時予約画面
│       ├── BasketObserver.java     # カート管理
│       ├── MyPage.java             # マイページ機能
│       ├── PayInfo.java            # 決済情報画面
│       ├── CouponPage.java         # クーポン機能
│       ├── Rank.java               # ポイントランク表示
│       ├── Notice.java             # お知らせ管理
│       └── Search.java             # メニュー検索機能
</pre>

---

### 🛠 使用技術

- **言語**: Java 11+
- **GUIツール**: Java Swing
- **構造**: オブジェクト指向設計に基づいた MVC アーキテクチャ
- **開発環境**: IntelliJ IDEA
- **デザイン**: Figma（画面設計用）
- **バージョン管理**: Git, GitHub
- **ドキュメント**: Markdown, README

---

![Portfolio-YJCAFE_page-0001](https://github.com/user-attachments/assets/6cc77a9f-cf8c-45fd-816d-c25ead7cec1f)
![Portfolio-YJCAFE_page-0002](https://github.com/user-attachments/assets/d170dcad-3edc-4b14-bc5f-4651344322ba)
![Portfolio-YJCAFE_page-0003](https://github.com/user-attachments/assets/6585b6f0-d868-4a7e-b1b1-1d8c37aafe8b)
![Portfolio-YJCAFE_page-0004](https://github.com/user-attachments/assets/509a33f2-2d94-4325-84d6-1ac6198131e6)
![Portfolio-YJCAFE_page-0005](https://github.com/user-attachments/assets/ea9c0836-bd5b-4114-b233-320b9b56d1c2)
![Portfolio-YJCAFE_page-0006](https://github.com/user-attachments/assets/6318b4b7-1b3a-4283-8374-94a9ffc59457)
![Portfolio-YJCAFE_page-0007](https://github.com/user-attachments/assets/28c48583-ea73-46ba-b869-4a71020b6b06)
![Portfolio-YJCAFE_page-0008](https://github.com/user-attachments/assets/065360bd-c7b7-4fd6-a231-a05003eccc01)
![Portfolio-YJCAFE_page-0009](https://github.com/user-attachments/assets/ad69b1c1-d43e-4b05-bf54-64e052b29f37)
![Portfolio-YJCAFE_page-0010](https://github.com/user-attachments/assets/295f7b2a-59f8-4fb3-a6bb-0ff4cd189fb8)
![Portfolio-YJCAFE_page-0011](https://github.com/user-attachments/assets/09960cfa-f5cb-4531-9a77-4be5c1c2ec92)
![Portfolio-YJCAFE_page-0012](https://github.com/user-attachments/assets/97ba5c88-75fb-435d-84cf-9313db3e4901)
![Portfolio-YJCAFE_page-0013](https://github.com/user-attachments/assets/de6d561f-16df-4495-8b3b-c5781afddc6c)
![Portfolio-YJCAFE_page-0014](https://github.com/user-attachments/assets/be801a67-9d4e-4ab3-81d5-71a8c38bd0ca)
![Portfolio-YJCAFE_page-0015](https://github.com/user-attachments/assets/a205d24d-782c-42b3-b81b-c413d9d42994)
![Portfolio-YJCAFE_page-0016](https://github.com/user-attachments/assets/e2a540ea-0f91-472b-acbf-ae8a5f3ceaf4)
![Portfolio-YJCAFE_page-0017](https://github.com/user-attachments/assets/cb4a9ecb-b6ae-40ac-a659-d89a874f7503)
![Portfolio-YJCAFE_page-0018](https://github.com/user-attachments/assets/820164d4-3ae7-4513-937d-a1a753a5d5ff)
![Portfolio-YJCAFE_page-0019](https://github.com/user-attachments/assets/66755d5e-286f-41fc-ab64-1f321cebfb55)
![Portfolio-YJCAFE_page-0020](https://github.com/user-attachments/assets/0bd5181a-563d-4745-adab-76a9dbaf03b8)
![Portfolio-YJCAFE_page-0021](https://github.com/user-attachments/assets/8a3c0b69-31e4-4228-84d0-d8e102a96f8c)
![Portfolio-YJCAFE_page-0022](https://github.com/user-attachments/assets/bc56f5ce-b419-4208-ae90-493fcea30d37)
![Portfolio-YJCAFE_page-0023](https://github.com/user-attachments/assets/eceac34c-4692-401e-aa76-2cab1c2bca55)
![Portfolio-YJCAFE_page-0024](https://github.com/user-attachments/assets/ce90fcd3-6a37-42b4-8099-d83f398942a2)
![Portfolio-YJCAFE_page-0025](https://github.com/user-attachments/assets/ae5a279a-027e-4012-9061-89f0b7a08784)
![Portfolio-YJCAFE_page-0026](https://github.com/user-attachments/assets/520a3b1e-9723-4c13-a2ce-c49340c2d658)
![Portfolio-YJCAFE_page-0027](https://github.com/user-attachments/assets/5439fb3c-a7fb-4196-82cd-1926e4f14c66)
![Portfolio-YJCAFE_page-0028](https://github.com/user-attachments/assets/09689a8d-fcc4-41c1-8d76-a126622d3ee8)
