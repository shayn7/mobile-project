package pages;

import mobilePlatforms.AbstractPlatform;

public enum Page {
    LaunchPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new LaunchPage(abstractPlatform);
        }
    },
    ExplorePage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new ExplorePage(abstractPlatform);
        }

    },
    WelcomePage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new WelcomePage(abstractPlatform);
        }

    },
    FacebookSignInPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new FacebookSignInPage(abstractPlatform);
        }
    },
    RecordPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new RecordPage(abstractPlatform);
        }

    },

    GoogleSignIn() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new GoogleSignIn(abstractPlatform);
        }

    },

    EmailSignUpPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new EmailSignUpPage(abstractPlatform);
        }
    },

    TrekSummaryPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new TrekSummaryPage(abstractPlatform);
        }
    },

    SavedTrekPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new SavedTrekPage(abstractPlatform);
        }
    },

    ChatPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new ChatPage(abstractPlatform);
        }
    },
    ChatsConversationPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new ChatsConversationPage(abstractPlatform);
        }
    },

    FavoritesPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new FavoritesPage(abstractPlatform);
        }
    },

    EmailSignInPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new EmailSignInPage(abstractPlatform);
        }
    },

    ProfilePage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new ProfilePage(abstractPlatform);
        }
    },

    SettingsPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) { return new SettingsPage(abstractPlatform);}
    },

    AboutPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) { return new AboutPage(abstractPlatform);}
    };


    public abstract AbstractPage getPage(AbstractPlatform abstractPlatform);
}
