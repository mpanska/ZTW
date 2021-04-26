import React, { useState } from 'react'
import Link from 'gatsby-link'
import './index.css'
import { graphql } from 'gatsby'
import Layout from "../components/layout"
import Img from "gatsby-image"

const IndexPage = ({data}) => {
  console.log(data)

  const [state, setState] = useState({
    filteredPosts: [],
    query: "",
  });

  const allPosts = data.allMarkdownRemark.edges;

  const handleInputChange = event => {
    const query = event.target.value;
    const filteredPosts = allPosts.filter(post => {
      const { title } = post.node.frontmatter;
      return (
        title.toLowerCase().includes(query.toLowerCase()) 
      );
    });
    setState({
      query,
      filteredPosts,
    });
  };

  const posts = state.query ? state.filteredPosts : allPosts;

  return(
    <Layout>
      <input
        type="text"
        aria-label="Search"
        placeholder="Search posts"
        value={state.query}
        onChange={handleInputChange}

        style={{marginBottom: '20px', width: '400px'}}
      />
      <div>
        {posts.map(({node}) => (
          <div key={node.id} className="article-box">
            <Link to={node.fields.slug} style={{
              textDecoration: 'none', color: 'inherit'
              }}>
              <h3 className="title">{node.frontmatter.title}</h3>
              
              <Img fluid={node.frontmatter.featuredImage.childImageSharp.fluid} style={{width: '520px', marginTop: '16px', matginBottom: '20px'}}/>  
            </Link>
            <p style={{marginTop: '14px'}} className="author">Author: <i>{node.frontmatter.author}</i></p>
            <p className="date">{node.frontmatter.date} {node.timeToRead} min read</p>
            <p className="excerpt">{node.excerpt}</p>
          </div>
        ))}
      </div>
    </Layout>
  )
}

export default IndexPage

export const query = graphql`
query HomePageQuery{
  allMarkdownRemark(sort: {fields: [frontmatter___date], order: DESC}) {
    totalCount
    edges {
      node {
        fields{
          slug
        }
        frontmatter {
          title
          date
          author
          featuredImage {
            childImageSharp {
              fluid(maxWidth: 2048, quality: 90) {
                ...GatsbyImageSharpFluid
              }
            }
          }
        } 
        excerpt
        timeToRead
      }
    }
  }
} 
`
