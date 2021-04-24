import React from "react";
import { graphql } from 'gatsby'
import Layout from "../components/layout"
import { DiscussionEmbed } from "disqus-react"
import Img from "gatsby-image"

export default ({ data }) => {    
    const post = data.markdownRemark;
    let featuredImgFluid = post.frontmatter.featuredImage.childImageSharp.fluid
    const disqusConfig = {
        shortname: "ztw-1",
        config: { identifier: post.frontmatter.title },
    }
    return (
        <Layout>
            <div>
                <h1>{post.frontmatter.title}</h1>
                <Img fluid={featuredImgFluid} />
                <h4 style={{color: 'rgb(165, 164, 164)'}}>{post.frontmatter.author} 
                    <span style={{fontSize: '0.8em'}}> -{post.frontmatter.date}</span>
                </h4>
                <div dangerouslySetInnerHTML = {{ __html: post.html }}/>
            </div>
            <DiscussionEmbed {...disqusConfig} />
        </Layout>
    );
};

export const query = graphql`
    query PostQuery($slug: String!) {
        markdownRemark(fields: { slug: { eq: $slug } }) {
            html
            frontmatter {
                title
                author
                date
                featuredImage {
                    childImageSharp {
                      fluid(maxWidth: 200) {
                        ...GatsbyImageSharpFluid
                      }
                    }
                }
            }
        }
    }
`;