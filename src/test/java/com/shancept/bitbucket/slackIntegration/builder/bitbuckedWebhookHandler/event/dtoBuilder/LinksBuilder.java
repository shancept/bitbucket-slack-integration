package com.shancept.bitbucket.slackIntegration.builder.bitbuckedWebhookHandler.event.dtoBuilder;

import com.shancept.bitbucket.slackIntegration.bitbuckedWebhookHandler.event.dto.Links;

import java.util.List;

final public class LinksBuilder {
    private List<Links.Link> self = List.of(new LinkBuilder().build());

    public LinksBuilder withSelf(List<Links.Link> self) {
        this.self = self;
        return this;
    }

    public Links build() {
        Links links = new Links();
        links.setSelf(self);
        return links;
    }

    public static class LinkBuilder {
        public final static String HREF = "href";
        private String href = HREF;

        public LinkBuilder withHref(String href) {
            this.href = href;
            return this;
        }

        public Links.Link build() {
            Links.Link link = new Links.Link();
            link.setHref(href);
            return link;
        }
    }
}
